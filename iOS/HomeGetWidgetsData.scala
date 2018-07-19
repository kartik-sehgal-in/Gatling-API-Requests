
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random


class HomeGetWidgetsData extends Simulation {
  val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
			val scn = scenario("HomeGetWidgetsData")
	.exec(http("request_0")
	.get("/service/personalization/v2/get/getWidgetsData?widgetType=tm&pg=hp&wt=tm&aid=29&appType=APP&appVersion=5.0.1&deviceId=B13F8B84-693F-478A-A93A-8F646156E06F&appPlatform=ios&jid=43&ruleId=44&limit=20&sp=4,5,8,14,21,27&aid=43&cs=4&ts=37&testId=A")
   .check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}