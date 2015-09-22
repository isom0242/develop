package controllers

import models.{ResponseParams, Geometry, RequestParams}
import libs.Utility
import play.api.{Logger, Play}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import play.api.libs.json.Json

import scala.sys.process.Process
import scala.io.Source
import scala.reflect.io.File

class Tracing extends Controller {

  private val logger = Logger(getClass)

  val req: Form[RequestParams] = Form(
    mapping(
      "fqdn" -> text,
      "seq" -> number
    )(RequestParams.apply)(RequestParams.unapply)
  )

   def index = Action {
     implicit request => req.bindFromRequest.fold(
      errors => BadRequest("error"),
      req => {
        val resParams = if (req.seq == 0) {
          // tracerouteコマンドを発行
          logger.info("start traceroute [fqdn: " + req.fqdn + " seq:" + req.seq + "]")
          execTrace(req.fqdn)
        } else {
          // traceroute情報を取得
          logger.info("get traceroute [fqdn: " + req.fqdn + " seq:" + req.seq + "]")
          getTraceResult(req.fqdn, req.seq)
        }
        implicit val geometryWrites = Json.writes[Geometry]
        implicit val resParamsWrites = Json.writes[ResponseParams]
        Ok(Json.toJson(resParams))
      }
     )
   }

  /**
   * tracerouteを実行
   *
   * @param fqdn FQDN
   * @return レスポンスパラメータ
   */
   def execTrace(fqdn: String): ResponseParams = {
     val filePath = Play.current.configuration.getString("traceroute.dir.path").get + fqdn
     Process("traceroute -n -w 1 -m 30 -q 1 " + fqdn) #> new java.io.File(filePath) run

     val ip = Utility.getIpAddressByFqdn(fqdn)
     var status = if (ip.length > 0) { 100 } else { -1 }
     ResponseParams(status, fqdn, ip, 0, 1, Geometry("", "", 0, 0))
   }

  /**
   * tracerouteの特定のホップ数目の情報を取得
   *
   * @param fqdn FQDN
   * @param seq シーケンス番号
   * @return レスポンスパラメータ
   */
   def getTraceResult(fqdn: String, seq: Int): ResponseParams = {
     val filePath = Play.current.configuration.getString("traceroute.dir.path").get + fqdn
     if (!File(filePath).exists) {
       BadRequest("error")
     }

     var routedIp = ""
     var hop = 1
     var searchSeq = seq
     var nextSeq = seq

     // tracerouteコマンドの結果ファイルの中身をイテレーション
     for(line <- Source.fromFile(filePath).getLines) {

       if (searchSeq == hop) {
         val ip: Option[String] = Utility.extractIpAddressByString(line)
         if (ip.isEmpty) {
           searchSeq += 1
         } else {
           routedIp = ip.get
           nextSeq = searchSeq + 1
         }
       }
       hop += 1
     }

     // tracerouteの結果が存在しなければ最終地点のIPアドレスを設定
     if (routedIp.length == 0) {
       routedIp = Utility.getIpAddressByFqdn(fqdn)
       nextSeq = -1
     }
     val geometry = Utility.getGeometryByIpAddress(routedIp)
     val status = if (geometry.latitude != 0 && geometry.longitude != 0) { 200 } else { 0 }

     ResponseParams(status, fqdn, routedIp, seq, nextSeq, geometry)
   }
 }
