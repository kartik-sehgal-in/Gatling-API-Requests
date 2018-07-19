import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random

class LoginResendOTP extends Simulation{
  var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4")  
   val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
	val scn = scenario("LoginResendOTP")
	.exec(http("request_0")
	.post("/service/user/otp/v1/resendOTP")
	.headers(sentHeaders)
	.body(StringBody("""
        {
	"requestProtocol": "PROTOCOL_JSON",
	"apiKey": "snapdeal",
	"responseProtocol": "PROTOCOL_JSON",
	"otpId": "SD:VjUwI2Y5YWI1YTYxLWM3ZDYtNDgxYy05ZWU1LWEyYzFiNzlkNmZmYSNfMDdfMjAxOA",
	"otpChannel": "THROUGH_SMS"
}
        """)).asJSON
   .check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}