import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class getSearchResults extends Simulation{
  
  val hp = http
    .baseURL("https://mobileapi.snapdeal.com")
    
    val scn = scenario("scenario")
      .exec(http("r0")
      .get("/service/get/search/v2/getSearchResults?apiKey=snapdeal&categoryId=0&categoryXPath=mens-footwear-casual-shoes&keyword=&number=12&pincode=122022&q=discount%3A40%20-%2050%5E50%20-%2060%5E60%20-%2070%5E70%20-%2080%5E80%20-%2090%7ChpsaScore_tf1%3A1%7C&requestProtocol=PROTOCOL_JSON&responseProtocol=PROTOCOL_JSON&searchStateV2=&sortBy=plrty&spellCheck=true&start=0&zone=Z9&av=6.5.6")
      .check(status.is(200))
      )
      
  setUp(scn.inject(atOnceUsers(1))).protocols(hp)
}