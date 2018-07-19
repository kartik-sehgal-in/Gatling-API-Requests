
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class PostGetTransferToken extends Simulation {

  val sentHeaders = Map ("v" -> "6.5.6", "Content-Type" -> "application/json;charset=utf-8", "os" -> "android", "os_version" -> "7.0")

	val httpProtocol = http
		.baseURL("https://mobileapi.snapdeal.com")
  
	val scn = scenario("scenario")
	.exec(http("request_1")
		.post("/service/user/login/v2/loginWithEmail")
    .headers(sentHeaders)

   .body(StringBody("""
{
	"password": "12chachacha",
	"emailId": "kartiksehgal3@gmail.com",
	"responseProtocol": "PROTOCOL_JSON",
	"apiKey": "snapdeal",
	"requestProtocol": "PROTOCOL_JSON"
}
                      """)).asJSON
    
    .check(status.is(200))
            .check(header("Login-Token").saveAs("Token"))
  )	
		
		.exec(http("request_0")
		.post("/service/user/v1/getTransferToken")
    .headers(Map ("v" -> "6.5.6", "Content-Type" -> "application/json;charset=utf-8", "os" -> "android", "os_version" -> "7.0", "Login-Token"-> "${Token}"))
    
   .body(StringBody("""
                        {
	"responseProtocol": "PROTOCOL_JSON",
	"apiKey": "snapdeal",
	"requestProtocol": "PROTOCOL_JSON",
	"targetTokenConsumer": "MSITE"
}
                      """)).asJSON
    
    .check(status.is(200))
)

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}