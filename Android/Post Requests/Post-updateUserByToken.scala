
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class PostUpdateUserByToken extends Simulation {

  val sentHeaders = Map ("v" -> "6.5.6", "Content-Type" -> "application/json;charset=utf-8", "os" -> "android", "os_version" -> "7.0")

	val httpProtocol = http
		.baseURL("https://mobileapi.snapdeal.com")
  
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
		.post("/service/user/updateUserByToken")
    .headers(Map ("v" -> "6.5.6", "Content-Type" -> "application/json;charset=utf-8", "os" -> "android", "os_version" -> "7.0", "Login-Token"-> "${Token}"))
    
   .body(StringBody("""
                        {
	"firstName": "",
	"middleName": "",
	"responseProtocol": "PROTOCOL_JSON",
	"dob": "03-Aug-1996",
	"requestProtocol": "PROTOCOL_JSON",
	"lastName": "",
	"gender": "Male",
	"displayName": "Kartik Sehgal",
	"apiKey": "snapdeal",
	"categories": [{
		"categoryName": "Men's Fashion",
		"imageUrl": "https:\/\/n1.sdlcdn.com\/imgs\/d\/j\/h\/NUX_imgs_mansgs-e8ee2.jpg",
		"subCatIds": [17, 226, 1242, 1311, 477]
	}, {
		"categoryName": "Books",
		"imageUrl": "https:\/\/i3.sdlcdn.com\/img\/storeFrontFeaturedProductAdmin\/11\/NUXimgsbookswr88583.jpg",
		"subCatIds": [364]
	}, {
		"categoryName": "Mobiles & Accessories",
		"imageUrl": "https:\/\/n1.sdlcdn.com\/imgs\/d\/j\/h\/NUX_imgs_mobilesegre-a04b3.jpg",
		"subCatIds": [175, 29, 1276]
	}, {
		"categoryName": "Laptops",
		"imageUrl": "https:\/\/n1.sdlcdn.com\/imgs\/d\/j\/h\/NUX_imgs_laptop-d999a.jpg",
		"subCatIds": [57]
	}]
}
                      """)).asJSON
    
    .check(status.is(200))
)

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}