import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class getDynamicFilters extends Simulation{
  
  val hp = http
    .baseURL("https://mobileapi.snapdeal.com")
    
    val scn = scenario("scenario")
      .exec(http("r0")
      .get("/service/get/filters/getDynamicFilters?apiKey=snapdeal&categoryId=0&categoryXPath=gaming-accessories&checkServiceable=false&filterQuery=&keyword=&partialSearch=false&pincode=122022&requestProtocol=PROTOCOL_JSON&responseProtocol=PROTOCOL_JSON&searchStateV2=k3%3Dtrue%7Ck4%3Dnull%7Ck5%3D0%7Ck6%3D0&zone=Z9&av=6.5.6")
      .check(status.is(200))
      )
      
  setUp(scn.inject(atOnceUsers(1))).protocols(hp)
}