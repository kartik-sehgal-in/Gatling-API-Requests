

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random

class NativeCartRemoveItemFromCart extends Simulation{
  var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4")  
   val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
	val scn = scenario("NativeCartRemoveItemFromCart")
	.exec(http("request_0")
	.post("/service/nativeCart/v2/removeItemFromCart")
	.headers(sentHeaders)
	.body(StringBody("""
        {
	"cartId": "33659d8f-57d4-42ea-9461-4db8487cf182",
	"itemsTORemove": [{
		"vendorCode": "S888d4",
		"catalogId": 642110460110,
		"supc": "SDL577663225"
	}]
}
        """)).asJSON
   .check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}