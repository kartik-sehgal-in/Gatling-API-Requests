import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random

class HomeGetBannerWidgetData extends Simulation{
  var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4")
  val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
			val scn = scenario("HomeGetBannerWidgetData")
	.exec(http("request_0")
	.get("/service/personalization/v2/getBannerWidgetData?bannerType=platinum&widgetType=APP_HOMEPAGE_CAROUSAL_PLATINUM_BANNER&widgetSequence=0")
  .headers(sentHeaders) 
	.check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}