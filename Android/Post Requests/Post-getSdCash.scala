
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class PostGetSdCash extends Simulation {

  val sentHeaders = Map ("v" -> "6.5.6", "Content-Type" -> "application/json;charset=utf-8", "os" -> "android", "os_version" -> "7.0")

	val httpProtocol = http
		.baseURL("https://mobapipreprod.snapdeal.com")
  
	val scn = scenario("scenario")
		.exec(http("request_0")
		.post("/service/myAccount/getSdCash")
    .headers(sentHeaders)
    
   .body(StringBody("""
                        {
                          "deviceId": "290b4bc0d40591e",
                          "responseProtocol": "PROTOCOL_JSON",
                          "version": "v3",
                          "requestProtocol": "PROTOCOL_JSON",
                          "wfDataRequired": "false",
                          "pageName": "tabbedHome",
                          "apiKey": "snapdeal"
                        }
                      """)).asJSON
    
    .check(status.is(200))
)

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}