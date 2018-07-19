import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random

class SearchCheckSearchRedirection extends Simulation{
  var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4")
  val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
			val scn = scenario("SearchCheckSearchRedirection")
	.exec(http("request_0")
	.get("/service/get/search/v2/checkSearchRedirection?allowGetaway=false&apiKey=snapdeal&binSearch=false&categoryId=0&categoryXPath=women-apparel-dress-material&cityUrl=&clickSrc=&emailId=&fetchGuides=1&keyword=&latitude=&longitude=&noOfResultsPerBin=12&number=12&onDemand=1&pageType=slp&pincode=&publisherId=1&q=Price%3A267%2C899%7ChpsaScore_tf1%3A1%7C&requestProtocol=PROTOCOL_JSON&responseProtocol=PROTOCOL_JSON&response_type=search_ad&sdId=&serveAds=false&siteId=105&slotId=sd-cr-search-1-12&slotName=&sortBy=plrty&spellCheck=true&start=0&vertical=product&zone=Z17")
  .headers(sentHeaders) 
	.check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}