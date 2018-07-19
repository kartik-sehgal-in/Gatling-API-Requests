

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random

class ReferralGetReferralCount extends Simulation{
   var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4")
  
  val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
	val scn = scenario("ReferralGetReferralCount")
	.exec(http("request_1")
	.post("/service/user/login/v2/loginWithEmail")
	.headers(sentHeaders)
	.body(StringBody("""
    {
	"requestProtocol": "PROTOCOL_JSON",
	"responseProtocol": "PROTOCOL_JSON",
	"emailId": "shivam.gandhi.in@snapdeal.com",
	"password": "Milkproduct_03"
  }        
"""))
  .check(header("Login-Token").saveAs("token") 
	))
	.pause(2)
	.exec(http("request_0")
	.post("/service/referralv2/getReferralCount")
	.headers(Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4","Login-Token" -> "${token}"))
	.body(StringBody("""
        {
	        "requestProtocol": "PROTOCOL_JSON",
	        "responseProtocol": "PROTOCOL_JSON"
        }
        """)).asJSON
   .check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}