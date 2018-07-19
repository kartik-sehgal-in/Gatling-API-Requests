
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter
import scala.util.Random

class OrdersRecentUpdatedOrders extends Simulation{
  val writer = {
    val fos = new java.io.FileOutputStream("D:/gatling-charts-highcharts-bundle-2.3.1/user-files/simulations/body.json")
    new java.io.PrintWriter(fos,true)
  }
  
  var sentHeaders = Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4")  
   val httpProtocol = http
		.baseURL("http://mobileapi.snapdeal.com")
		
	val scn = scenario("OrdersRecentUpdatedOrders")
	.exec(http("request_1")
	.post("/service/user/login/v2/loginWithEmail")
	.headers(sentHeaders)
	.body(StringBody("""
    {
	"requestProtocol": "PROTOCOL_JSON",
	"responseProtocol": "PROTOCOL_JSON",
	"emailId": "shivam.gandhi.in@snapdeal.com",
	"password": "Milkproduct_03"
  }        
"""))
  .check(header("Login-Token").saveAs("token") 
	))
	.pause(2)
	
	.exec(http("request_0")
	.post("/service/order/recentUpdatedOrders")
	.headers(Map("v" -> "5.0.1","Content-Type" -> "application/json","os" -> "ios","os_version" -> "9.3.4","Login-Token" -> "${token}"))
	.body(StringBody("""
        {
	"pageSize": 5,
	"source": "",
	"channel": "APP",
	"pageNumber": 0,
	"requiredPaymentInfo": false,
	"requestProtocol": "PROTOCOL_JSON",
	"responseProtocol": "PROTOCOL_JSON",
	"email": "VjUwIzUzZTk3NmJlLTExMmQtNDEwMy05ZmU4LTFkMzZiMGM0ZWE5Yw@snpde.in",
	"lastInvocationTS": 0
}
        """)).asJSON
   .check(status.is(200))
   .check(bodyString.saveAs("Response_Data"))
   
   )
   .exec(session => {
      println("Some Restful Service: ")  
//      println(session("Response_Data").as[String])
//      session
      writer.println("Body = " + session("Response_Data").as[String])
      session
    })
   
   setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}