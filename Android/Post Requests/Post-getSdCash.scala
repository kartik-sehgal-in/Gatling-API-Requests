
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class PostGetSdCash extends Simulation {

  val sentHeaders = Map ("v" -> "6.5.6", "Content-Type" -> "application/json;charset=utf-8", "os" -> "android", "os_version" -> "7.0")

	val httpProtocol = http
		.baseURL("https://mobileapi.snapdeal.com")

	val scn = scenario("scenario")
	 .exec(http("request_0")
	 .post("/service/myAccount/getSdCash")
	 .headers(sentHeaders)  
   .body(StringBody("""
                        {
	"start": "0",
	"responseProtocol": "PROTOCOL_JSON",
	"mode": "cr",
	"requestProtocol": "PROTOCOL_JSON",
	"apiKey": "snapdeal",
	"noOfHistory": "10"
}
                      """)).asJSON
    
    .check(status.is(200))
)

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}