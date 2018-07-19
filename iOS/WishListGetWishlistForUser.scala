
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random


class WishListGetWishlistForUser extends Simulation {
  var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4")  
   val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
	val scn = scenario("WishListGetWishlistForUser")
	.exec(http("request_0")
	.post("/service/wishlist/getWishlistForUser")
	.headers(sentHeaders)
	.body(StringBody("""
        {
	"requestProtocol": "PROTOCOL_JSON",
	"responseProtocol": "PROTOCOL_JSON",
	"count": 50,
	"start": 0
}
        """)).asJSON
   .check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}