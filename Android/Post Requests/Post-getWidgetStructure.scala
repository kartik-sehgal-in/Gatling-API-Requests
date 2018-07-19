
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class PostGetWidgetStructure extends Simulation {

  val sentHeaders = Map ("v" -> "6.5.6", "Content-Type" -> "application/json;charset=utf-8", "os" -> "android", "os_version" -> "7.0")

	val httpProtocol = http
		.baseURL("https://mobileapi.snapdeal.com")
  
	val scn = scenario("scenario")
		.exec(http("request_0")
		.post("/service/personalization/v3/getWidgetStructure")
    .headers(sentHeaders)
    
   .body(StringBody("""
{
	"deviceId": "290b4bc0d40591e",
	"emailId": "",
	"zone": null,
	"responseProtocol": "PROTOCOL_JSON",
	"pincode": null,
	"mobile": null,
	"version": "v3",
	"requestProtocol": "PROTOCOL_JSON",
	"wfDataRequired": "false",
	"pageName": "tabbedHome",
	"apiKey": "snapdeal",
	"rawUrl": ""
}
                      """)).asJSON
    
    .check(status.is(200))
)

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}