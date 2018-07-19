
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random

class HomeGetWidgetStructure9 extends Simulation{
  var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4")  
   val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
	val scn = scenario("HomeGetWidgetStructure9")
	.exec(http("request_0")
	.post("/service/personalization/v3/getWidgetStructure")
	.headers(sentHeaders)
	.body(StringBody("""
       {
	"deviceId": "B13F8B84-693F-478A-A93A-8F646156E06F",
	"mobile": "",
	"apiKey": "snapdeal",
	"rawUrl": "",
	"pageName": "FeatureFlag",
	"emailId": "",
	"requestProtocol": "PROTOCOL_JSON",
	"pincode": "",
	"version": "v3",
	"responseProtocol": "PROTOCOL_JSON"
}
        """)).asJSON
   .check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}