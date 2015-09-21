package libs

import java.net.InetAddress
import models.Geometry
import play.api.Logger
import play.api.libs.ws.{WS, WSResponse}
import play.api.Play.current
import play.api.cache.Cache
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object Utility {

  private val logger = Logger(getClass())

  /**
   * 文字列からIPアドレスを抽出
   *
   * <p>tracerouteコマンドの結果<code>(xxx.xxx.xxx.xxx)</code>が文字列の中に含まれている必要がある</p>
   *
   * @param str 文字列
   * @return 文字列に含まれるIPアドレス
   */
  def extractIpAddressByString(str: String): Option[String]  = {
  val pattern = """^\s?\d+.*\((\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3})\).*""".r
    str match {
      case pattern(ip) =>  Some(ip)
      case _ => None
    }
  }

  /**
   * IPアドレスから地理情報を取得
   *
   * <p>キャッシュに保存されていなければ新規に地理情報を外部サーバから取得する</p>
   *
   * @param ip IPアドレス
   * @return 地理情報
   */
  def getGeometryByIpAddress(ip: String): Geometry = {
    // キャッシュされている情報があれば優先
    val geometry: Option[Geometry] = Cache.getAs[Geometry](ip)
    if (geometry.isEmpty) {
      logger.info(ip + "geometry get from server")
      val holder: Future[WSResponse] = WS.url("http://www.geoplugin.net/json.gp?ip=" + ip).get
      val res: WSResponse = Await.result(holder, Duration(10000, MILLISECONDS))
      val geometry = Geometry(ip, (res.json \ "geoplugin_countryName").as[String],
        (res.json \ "geoplugin_latitude").as[String].toDouble, (res.json \ "geoplugin_longitude").as[String].toDouble)

      // 一定時間キャッシュに保存
      Cache.set(ip, geometry, 100000)
      geometry
    } else {
      logger.info(ip + "geometry get from cache")
      geometry.get
    }
  }

  /**
   * FQDNをIPアドレスにして取得
   *
   * <p>FQDNが正しくない場合は空文字を返す</p>
   *
   * @param fqdn FQDN
   * @return IPアドレス
   */
  def getIpAddressByFqdn(fqdn: String): String = {
    try {
      InetAddress.getByName(fqdn).getHostAddress
    } catch {
      case e:Exception => ""
    }

  }
}
