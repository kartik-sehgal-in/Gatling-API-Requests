import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class getWidgetsData extends Simulation{
  
  val hp = http
    .baseURL("https://mobileapi.snapdeal.com")
    
    val scn = scenario("scenario")
      .exec(http("r0")
      .get("/service/personalization/v2/get/getWidgetsData?aid=29&appPlatform=android&appType=APP&appVersion=6.5.6&count=10&cs=1&deviceId=290b4bc0d40591e&jid=43&limit=15&pg=hp&ruleId=44&sp=1%2C2%2C3%2C4%2C5%2C6&start=0&testId=A&ts=39&widgetType=tm&wt=tm&av=6.5.6")
      .check(status.is(200))
      )
      
  setUp(scn.inject(atOnceUsers(1))).protocols(hp)
}