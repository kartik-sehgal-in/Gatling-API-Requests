import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random

class NativeCartMergeCart extends Simulation{
  var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4")  
   val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
	val scn = scenario("NativeCartMergeCart")
	.exec(http("request_0")
	.post("/service/nativeCart/v2/mergeCart")
	.headers(sentHeaders)
	.body(StringBody("""
        {
	"cartId": "c62795da-80f2-41a0-b962-189d5a21d265",
	"loginToken": "SD:Vzg3Y7c_liGZYGiBRgVWo3HKHu_4KzZFJnDO1fEHeCtimML9y8NwdUHndrcux9Qje6lj3mHyyYSwX6MdkSlli_nt-d0BWO6fVtmXbM2O5to_4yybufWtBmab1NOULm7S",
	"pincode": "110045"
}
        """)).asJSON
   .check(status.is(200))
   )
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}