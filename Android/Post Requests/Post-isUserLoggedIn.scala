
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class PostIsUserLoggedIn extends Simulation {

	val httpProtocol = http
		.baseURL("https://mobileapi.snapdeal.com")
  	
	val writer = {
    val fos = new java.io.FileOutputStream("D:/gatling-charts-highcharts-bundle-2.3.1/user-files/simulations/body.json")
    new java.io.PrintWriter(fos,true)
}
  
	val scn = scenario("scenario")
    .exec(http("request_0")
		.post("/service/isUserLoggedIn")
    .headers(Map ("v" -> "6.5.6", "Content-Type" -> "application/json;charset=utf-8", "os" -> "android", "os_version" -> "7.0"))
    
   .body(StringBody("""
  {
	"apiKey": "snapdeal",
	"requestProtocol": "PROTOCOL_JSON",
	"responseProtocol": "PROTOCOL_JSON"
}
               """)).asJSON
    
    .check(status.is(200))

)
	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}