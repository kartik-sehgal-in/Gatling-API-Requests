
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random

class NativeInsertItemToCart extends Simulation{
  var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4")  
   val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
	val scn = scenario("NativeInsertItemToCart")
	.exec(http("request_0")
	.post("/service/nativeCart/v2/insertItemToCart")
	.headers(sentHeaders)
	.body(StringBody("""
        {
	"items": [{
		"vendorCode": "Sd1f79",
		"catalogId": "630794486206",
		"supc": "SDL983268903",
		"quantity": "1"
	}]
}
        """)).asJSON
   .check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}