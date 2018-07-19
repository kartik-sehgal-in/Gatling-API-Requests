
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class PostGetCart extends Simulation {

  val sentHeaders = Map ("v" -> "6.5.6", "Content-Type" -> "application/json;charset=utf-8", "os" -> "android", "os_version" -> "7.0")

	val httpProtocol = http
		.baseURL("https://mobileapi.snapdeal.com")
  
	val writer = {
    val fos = new java.io.FileOutputStream("D:/gatling-charts-highcharts-bundle-2.3.1/user-files/simulations/body.json")
    new java.io.PrintWriter(fos,true)
}
  
	val scn = scenario("scenario")
	.exec(http("request_1")
		.post("/service/user/login/v2/loginWithEmail")
    .headers(sentHeaders)

   .body(StringBody("""
{
	"password": "12chachacha",
	"emailId": "kartiksehgal3@gmail.com",
	"responseProtocol": "PROTOCOL_JSON",
	"apiKey": "snapdeal",
	"requestProtocol": "PROTOCOL_JSON"
}
                      """)).asJSON
    
    .check(status.is(200))
            .check(header("Login-Token").saveAs("Token"))
  )	
	
		.exec(http("request_0")
		.post("/service/nativeCart/v2/getCart")
    .headers(sentHeaders)
    
   .body(StringBody("""
                          {
                           	"loginToken": "${Token}",
                          	"pincode": "122022"
                          }                      """)).asJSON
    
    .check(status.is(200))
    .check(bodyString.saveAs("Body"))
)

    .exec {session =>
  writer.println("Body = " + session("Body").as[String])
  session
  
  }

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}