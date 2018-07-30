
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import scala.util.Random

class randomDeviceId extends Simulation {

  val sentHeaders = Map ("v" -> "6.5.6", "Content-Type" -> "application/json;charset=utf-8", "os" -> "android", "os_version" -> "7.0")

	val httpProtocol = http
		.baseURL("https://mobapipreprod.snapdeal.com")
		  
 val infiniteFeeder = Iterator.continually(
   Map("Key" -> Random.alphanumeric.take(15).mkString)
) 
		
	val writer = {
    val fos = new java.io.FileOutputStream("D:/gatling-charts-highcharts-bundle-2.3.1/user-files/simulations/body.json")
    new java.io.PrintWriter(fos,true)
}
  
	val scn = scenario("RecordedSimulation")
	  .feed(infiniteFeeder)
		.exec(http("request_0")
		.post("/service/personalization/v3/getWidgetStructure")
    .headers  (sentHeaders)
    
   .body(StringBody("""
                        {
                          "deviceId": "${Key}",
                          "responseProtocol": "PROTOCOL_JSON",
                          "version": "v3",
                          "requestProtocol": "PROTOCOL_JSON",
                          "wfDataRequired": "false",
                          "pageName": "tabbedHome",
                          "apiKey": "snapdeal"
                        }
                      """)).asJSON
    
    .check(status.is(200))
    .check(bodyString.saveAs("Body"))
)
.exec {session =>
  writer.println("Body = " + session("Body").as[String])
  session
  
  }

	setUp(scn.inject(atOnceUsers(2))).protocols(httpProtocol)
}