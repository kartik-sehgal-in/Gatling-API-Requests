import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random

class PdpGetInfiniteFeed extends Simulation{
  var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4")
  val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
			val scn = scenario("PdpGetInfiniteFeed")
	.exec(http("request_0")
	.get("/service/personalization/pdp/getInfiniteFeed?limit=15&pg=pp&sp=18,23&cs=18&ts=23&ruleId=356&jid=45&testId=A&deviceId=B13F8B84-693F-478A-A93A-8F646156E06F&appVersion=5.0.1&appType=APP&appPlatform=ios&pid=620665329699&totalPogCount=0&adsPogCount=0&brandPogCount=0&pricePogCount=0&pogListCode=0&followUpId=tm&price=2496&brand=Timex&categoryXPath=watches-men")
  .headers(sentHeaders) 
	.check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}