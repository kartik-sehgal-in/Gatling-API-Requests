import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random


class LoginSendMobileLoginOTP extends Simulation{
  var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4")  
   val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
	val scn = scenario("LoginSendMobileLoginOTP")
	.exec(http("request_0")
	.post("/service/user/otp/v1/sendMobileLoginOTP")
	.headers(sentHeaders)
	.body(StringBody("""
       {
	"status": "SUCCESS",
	"successful": true,
	"validationErrors": [],
	"data": {
		"otpId": "SD:VjUwIzcyMTI1NmZkLWVkNjktNGJkZi05ZDgwLTE1ZGE1NDE0MDM3ZiNfMDdfMjAxOA",
		"otpMessage": "Please enter verification code sent to 9654421103",
		"callMeFeatureEnableTimeout": 30,
		"callMeEnabled": false
	}
}
        """)).asJSON
   .check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}