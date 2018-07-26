import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class pdp extends Simulation{

  val sentHeaders = Map ("v" -> "6.5.6", "Content-Type" -> "application/json;charset=utf-8", "os" -> "android", "os_version" -> "7.0")

  val hp = http
    .baseURL("https://mobileapi.snapdeal.com")
    
	val writer = {                  //writes to a file. for checking. may remove
    val fos = new java.io.FileOutputStream("D:/gatling-charts-highcharts-bundle-2.3.1/user-files/simulations/body.json")
    new java.io.PrintWriter(fos,true)
}
   var totalPogCount:String = "0"
   var adsPogCount:String = "0"
   var pricePogCount:String = "0"
   var brandPogCount:String = "0"
   var followUpId:String = "tm"
   var pogListCode:String = ""

  
  val scn = scenario("scenario")
  .exec(http("r0")
      .get("/service/get/product/getProductDetails")
      .queryParam("apiKey","snapdeal")
      .queryParam("pincode","122022")
      .queryParam("productId","219723")
      .queryParam("requestProtocol","PROTOCOL_JSON")
      .queryParam("responseProtocol","PROTOCOL_JSON")
      .check(status.is(200))
      )
      
      .exec(http("request_0")
		  .post("/service/personalization/v3/getWidgetStructure")
      .headers(sentHeaders)
      .body(StringBody("""
{
	"zone": "Z26",
	"responseProtocol": "PROTOCOL_JSON",
	"pincode": "110048",
  "appType": "SNAPLITE",
	"version": "v3",
  "pogId": "219723",
	"requestProtocol": "PROTOCOL_JSON",
  "emailId": "anurag.kumar@snapdeal.com",
  "mobile": "8939727529",
	"pageName": "tabbedPdp",
	"apiKey": "snapdeal"
}
                      """)).asJSON
    
    .check(status.is(200))
)

      .exec(http("r0")
      .get("/service/get/product/verifyPinCode?requestProtocol=PROTOCOL_JSON&apiKey=snapdeal&responseProtocol=PROTOCOL_JSON&vendorCode=&catPageURL=null&brandName=null&appType=APP&zone=&supc=SDL384749364&pincode=110048")
      .check(status.is(200))
      )
      .exec(http("r0")
      .get("https://sga.snapdeal.biz/pub/getContent?pubId=1&rs=json&templateId=wapPdp2&slotId=sd-cr-130X152-1-3&siteId=102&response_type=similar&category=watches-men&pagetype=pdp&sdId=152938387535774110&max_val=12&min_val=4&pincode=110048&pogId=219723&eId=%22kartiksehgal3%40gmail.com%22")
      .check(status.is(200))
      )
      
      .exec(http("r0")
      .get("https://sgb.snapdeal.biz/pub/getContent?pubId=1&rs=json&templateId=bannerwap1&slotId=sd-bn-640X200-1-1&siteId=102&response_type=banner&category=watches-men&pagetype=pdp&sdId=152938387535774110&eId=%22kartiksehgal3%40gmail.com%22")
      .check(status.is(200))
      )
      
       exec(_.set("totalPogCount","0")).
       exec(_.set("adsPogCount","0")).
       exec(_.set("pricePogCount","0")).
       exec(_.set("brandPogCount","0")).
       exec(_.set("followUpId","tm")).
       exec(_.set("pogListCode","")).
       repeat(5)(
       exec(http("r5")
      .get("/service/personalization/pdp/getInfiniteFeed")
      .queryParam("siteId","102")
      .queryParam("serveAds",true)
      .queryParam("testId","A")
      .queryParam("ts","23")
      .queryParam("pg","pp")
      .queryParam("sp","18,23")
      .queryParam("phone","8939727529")
      .queryParam("zone","Z26")
      .queryParam("cookie","152938387535774110")
      .queryParam("appVersion","6.1.2")
      .queryParam("appType","SNAPLITE")
      .queryParam("appPlatform","android")
      .queryParam("pincode","110048")
      .queryParam("email","anurag.kumar@snapdeal.com")
      .queryParam("pid","219723")
      .queryParam("totalPogCount","${totalPogCount}")
      .queryParam("adsPogCount","${adsPogCount}")
      .queryParam("pricePogCount","${pricePogCount}")
      .queryParam("brandPogCount","${brandPogCount}")
      .queryParam("followUpId","${followUpId}")
      .queryParam("pogListCode","${pogListCode}")
      .queryParam("categoryXPath","watches-men")
      .queryParam("brandFilter","Fastrack")
      .queryParam("priceFilter","700,900")
      .check(status.is(200))
      .check(jsonPath("$.totalPogCount").exists.saveAs("totalPogCount"))
      .check(jsonPath("$.adsPogCount").exists.saveAs("adsPogCount"))
      .check(jsonPath("$.pricePogCount").exists.saveAs("pricePogCount"))
      .check(jsonPath("$.brandPogCount").exists.saveAs("brandPogCount"))
      .check(jsonPath("$.followUpId").exists.saveAs("followUpId"))
      .check(jsonPath("$.pogListCode").exists.saveAs("pogListCode"))
      .check(bodyString.saveAs("Body"))
  )
      .exec {session =>                                          //writes to a file. for checking. may remove
  writer.println("Body = " + session("Body").as[String])
  session
  
  }
)
setUp(scn.inject(atOnceUsers(1))).protocols(hp)
}