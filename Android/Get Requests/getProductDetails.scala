import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class getProductDetails extends Simulation{
  
  val hp = http
    .baseURL("https://mobileapi.snapdeal.com")
    
    val scn = scenario("scenario")
      .exec(http("r0")
      .get("/service/get/product/getProductDetails?apiKey=snapdeal&bundlingVersion=V2&pincode=122022&productId=681046259283&requestProtocol=PROTOCOL_JSON&responseProtocol=PROTOCOL_JSON&zone=Z9&av=6.5.6")
      .check(status.is(200))
      )
      
  setUp(scn.inject(atOnceUsers(1))).protocols(hp)
}