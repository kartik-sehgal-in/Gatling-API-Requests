import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class getInfiniteFeed extends Simulation{
  
  val hp = http
    .baseURL("https://mobileapi.snapdeal.com")
    
    val scn = scenario("scenario")
      .exec(http("r0")
      .get("/service/get/widget/getInfiniteFeed?adsPogCount=0&appPlatform=android&appType=APP&appVersion=6.5.6&count=10&cs=18&dpPogCount=0&expression=system.productFeed_category_all&jid=45&pg=hp&pincode=122022&ruleId=356&showAds=true&showNudge=true&siteId=103&sp=18%2C23&start=0&testId=A&ts=23&zone=Z9&av=6.5.6")
      .check(status.is(200))
      )
      
  setUp(scn.inject(atOnceUsers(1))).protocols(hp)
}