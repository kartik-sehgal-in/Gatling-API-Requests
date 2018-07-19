import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random

class HomeGetBanners extends Simulation{
   var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4")
  val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
			val scn = scenario("HomeGetBanners")
	.exec(http("request_0")
	.get("/service/get/widget/getBanners?apiKey=snapdeal&noOfBanners=300&requestProtocol=PROTOCOL_JSON&responseProtocol=PROTOCOL_JSON&start=0")
  .headers(sentHeaders) 
	.check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}