
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class PostGetListDataForUser extends Simulation {

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
		.post("/service/wishlist/getListDataForUser")
    .headers(Map ("v" -> "6.5.6", "Content-Type" -> "application/json;charset=utf-8", "os" -> "android", "os_version" -> "7.0", "Login-Token"-> "${Token}"))        
   .body(StringBody("""
                       {
	"deviceId": "290b4bc0d40591e",
	"networkType": "wifi",
	"count": "50",
	"api_key": "snapdeal",
	"User-Agent": "Xiaomi Redmi Note 4",
	"Accept-Encoding": "gzip",
	"os": "android",
	"v": "6.5.6",
	"androidId": "290b4bc0d40591e",
	"visitor_id": "25B236A1C6334F07-281CC0266240A577",
	"lan_ip": "10.20.86.25",
	"os_version": "7.0",
	"instanceId": "e1b68f81-0267-43ee-b6c7-51a7d4d2acad"
}
                      """)).asJSON
    
    .check(status.is(200))
)

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}