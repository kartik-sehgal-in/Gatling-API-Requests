import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random


class PlpGetDynamicFilters extends Simulation{
  var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4")
  val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
			val scn = scenario("PlpGetDynamicFilters")
	.exec(http("request_0")
	.get("/service/get/filters/getDynamicFilters?apiKey=snapdeal&categoryId=0&categoryXPath=women-apparel-dress-material&filterQuery=Price%3A267%2C899%7ChpsaScore_tf1%3A1%7C&keyword=&requestProtocol=PROTOCOL_JSON&responseProtocol=PROTOCOL_JSON&vertical=product&zone=Z17")
  .headers(sentHeaders) 
	.check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}