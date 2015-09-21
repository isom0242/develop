package models

case class RequestParams(fqdn: String, seq: Int)
case class ResponseParams(status: Int, fqdn: String, ip: String, seq: Int, nextseq: Int, geometry: Geometry)