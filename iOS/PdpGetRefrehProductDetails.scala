import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random

class PdpGetRefrehProductDetails extends Simulation{
   var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4") 
  val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
			val scn = scenario("HomeGetWidgetsData")
	.exec(http("request_0")
	.get("/service/get/product/getRefreshProductDetails?apiKey=snapdeal&pincode=110045&requestProtocol=PROTOCOL_JSON&responseProtocol=PROTOCOL_JSON&supc=SDL139991234&zone=Z26")
  .headers(sentHeaders) 
	.check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}