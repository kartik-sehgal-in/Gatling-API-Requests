import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class getBundleProducts extends Simulation{
  
  val hp = http
    .baseURL("https://mobileapi.snapdeal.com")
    
    val scn = scenario("scenario")
      .exec(http("r0")
      .get("/service/get/bundle/getBundleProducts?bundleIndex=1&bundleType=ALL&bundlingVersion=V2&pageNumber=0&pageSize=10&pincode=122022&requestProtocol=PROTOCOL_JSON&responseProtocol=PROTOCOL_JSON&supc=SDL158067138&av=6.5.6")
      .check(status.is(200))
      )
      
  setUp(scn.inject(atOnceUsers(1))).protocols(hp)
}