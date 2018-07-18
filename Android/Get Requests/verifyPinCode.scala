import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class verifyPinCode extends Simulation{
  
  val hp = http
    .baseURL("https://mobileapi.snapdeal.com")
    
    val scn = scenario("scenario")
      .exec(http("r0")
      .get("/service/get/product/verifyPinCode?apiKey=snapdeal&checkExchangeServiceable=false&o2oVendorCode=&pincode=122022&requestProtocol=PROTOCOL_JSON&responseProtocol=PROTOCOL_JSON&supc=SDL158067138&vendorCode=&zone=Z9&av=6.5.6")
      .check(status.is(200))
      )
      
  setUp(scn.inject(atOnceUsers(1))).protocols(hp)
}