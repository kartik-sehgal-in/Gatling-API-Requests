
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random

class LoginIsUserLoggedIn extends Simulation {
  var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4")  
   val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
	val scn = scenario("LoginIsUserLoggedIn")
	.exec(http("request_0")
	.post("/service/isUserLoggedIn")
	.headers(sentHeaders)
	.body(StringBody("""
        {
	"responseProtocol": "PROTOCOL_JSON",
	"loginToken": "SD:Vzg3Y7c_liGZYGiBRgVWo3DNmpOInFkIW_5BIxvTwNLk-RYAzZnB2IBHKQJeD10ouo3mZ5x7634xqOGkVPWvMU_CpPupiL5lK2-XaNbMd9vblbLr0DDXQATUt3hnCpMJ",
	"requestProtocol": "PROTOCOL_JSON"
}
        """)).asJSON
   .check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}