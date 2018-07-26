import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class getSearchResults extends Simulation{
  
  val hp = http
    .baseURL("https://mobileapi.snapdeal.com")
/*    		
	val writer = {
    val fos = new java.io.FileOutputStream("D:/gatling-charts-highcharts-bundle-2.3.1/user-files/simulations/body.json")
    new java.io.PrintWriter(fos,true)
}*/
  
  val feeder = csv("top_keywords_10k.csv").random
  
  val scn = scenario("scenario")
      .feed(feeder)
       .forever{
    exec(http("r0")
      .get("/service/get/search/v2/getSearchResults")
      .queryParam("number","1")
      .queryParam("keyword","${keyword}")
      .check(status.is(200))
   //       .check(bodyString.saveAs("Body"))

      )}
     /* .exec {session =>
  writer.println("Body = " + session("Body").as[String])
  session
  
  }*/

//  setUp(scn.inject(rampUsers(100) over (2 minutes))).maxDuration(5 minutes).protocols(hp)
  setUp(scn.inject(constantUsersPerSec(40) during (60 seconds))).maxDuration(2 minutes).protocols(hp)
}