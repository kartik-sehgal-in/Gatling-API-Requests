
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class PostInsertItemToCart extends Simulation {

  val sentHeaders = Map ("v" -> "6.5.6", "Content-Type" -> "application/json;charset=utf-8", "os" -> "android", "os_version" -> "7.0")

	val httpProtocol = http
		.baseURL("https://mobileapi.snapdeal.com")

	val scn = scenario("scenario")
		.exec(http("request_0")
		.post("/service/nativeCart/v2/insertItemToCart")
    .headers(sentHeaders)
    
   .body(StringBody("""
                       {
	"pincode": "122022",
	"items": [{
		"catalogId": 648763129960,
		"supc": "SDL882835290",
		"vendorCode": "S25c97",
		"quantity": 1
	}]
}
                      """)).asJSON
    
    .check(status.is(200))
)
	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}