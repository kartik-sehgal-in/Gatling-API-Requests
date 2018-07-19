
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random

class HomeGetZone extends Simulation{
  var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4")  
   val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
	val scn = scenario("HomeGetZone")
	.exec(http("request_0")
	.post("/service/getZone")
	.headers(sentHeaders)
	.body(StringBody("""
        {
	"requestProtocol": "PROTOCOL_JSON",
	"apiKey": "snapdeal",
	"responseProtocol": "PROTOCOL_JSON",
	"latitude": "",
	"longitude": "",
	"pincode": ""
}
        """)).asJSON
   .check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}