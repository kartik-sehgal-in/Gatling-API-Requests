import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.io.File
import java.io.PrintWriter

class UseCase1 extends Simulation{
  
   val writer = {
    val fos = new java.io.FileOutputStream("D:/gatling-charts-highcharts-bundle-2.3.1/user-files/simulations/xyz.json")
    new java.io.PrintWriter(fos,true)
   }

  var start:Int = 10 
  var sentHeaders = Map("v" -> "6.1.2","Content-Type" -> "application/json","os" -> "android")
   val httpProtocol = http.baseURL("https://mobileapi.snapdeal.com")

   val scn = scenario("UseCase1")
   .exec(http("request_0")
       .get("https://mobileapi.snapdeal.com/service/get/personalization/v3/getWidgetStructure?apiKey=snapdeal&pageName=home&appType=SNAPLITE&version=v3&cookieId=153027304683537947&pincode=&responseProtocol=PROTOCOL_JSON&requestProtocol=PROTOCOL_JSON")
       .headers(sentHeaders)
       .check(status.is(200))
   )
   
   .exec(http("request_1")
       .get("https://log.snapdeal.com/getEventData?event={%22orgId%22:%221001_1%22,%22email%22:null,%22cookieId%22:%22153027304683537947%22,%22appType%22:%22snaplite%22,%22browserDetails%22:%22Mozilla/5.0%20(Linux;%20Android%205.0;%20SM-G900P%20Build/LRX21T)%20AppleWebKit/537.36%20(KHTML,%20like%20Gecko)%20Chrome/67.0.3396.99%20Mobile%20Safari/537.36%22,%22eventTypes%22:[{%22eventType%22:%22webEvent%22,%22eventName%22:%22visitTracker%22,%22timestamp%22:%222018-07-24%2014:22:22.404%22,%22eventValuesV2%22:[{%22pageName%22:%22homePage%22}]}]}")
       .headers(sentHeaders)
       .check(status.is(200))
       )
   
   .exec(http("request_2")
       .post("/service/personalization/getApiResponseData")
       .headers(sentHeaders)
       .body(RawFileBody("D:/gatling-charts-highcharts-bundle-2.3.1/user-files/simulations/getApiResData")).asJSON
       .body(StringBody("""
        {"banner_platinum__0":{"methodType":"GET","apiUrl":"https://mobileapi.snapdeal.com/service/personalization/v2/getBannerWidgetData?bannerType=platinum&widgetType=APP_HOMEPAGE_CAROUSAL_PLATINUM_BANNER&widgetSequence=0"},"2x2_product_grid__2":{"methodType":"GET","apiUrl":"https://mobileapi.snapdeal.com/service/personalization/v2/get/getWidgetsData?widgetType=tm&pg=hp&wt=tm&aid=29&appType=SNAPLITE&appPlatform=android&cookie=153027304683537947&appVersion=6.1.2&ruleId=44&testId=A&limit=15&ts=39&cs=1&sp=1,2,3,4,5,6&jid=43&aid=43"}}
        """))
       .check(status.is(200))
       )

   .exec(http("request_3")
       .post("/service/nativeCart/v2/getCart")
       .headers(sentHeaders)
       .body(StringBody("""
        {"cartId": "57bced40-14b5-46f7-ba3f-7f97a6528a83"}
        """))
       .check(status.is(200))
       )
    .exec(_.set("start",10)).exec(http("request_4")
     .get("/service/get/widget/getInfiniteFeed")
     .headers(sentHeaders)
     .queryParam("expression","pog.HomePageD")
     .queryParam("pg","hp")
     .queryParam("appVersion","6.1.2")
     .queryParam("appPlatform","android")
     .queryParam("appType","SNAPLITE")
     .queryParam("start","${start}")
     .queryParam("count","10")
     .queryParam("adsPogCount","0")
     .queryParam("dpPogCount","0")
     .check(status.is(200))
     .check(bodyString.saveAs("Response_Data"))
     .check(jsonPath("$.adsPogCount").saveAs("adspgCnt"))
     .check(jsonPath("$.dpPogCount").saveAs("dppgCnt"))   
     )   
     
  
  .repeat(4) {
      start = start + 10
      exec(http("request_4")
     .get("/service/get/widget/getInfiniteFeed")
     .queryParam("expression","pog.HomePageD")
     .queryParam("pg","hp")
     .queryParam("appVersion","6.1.2")
     .queryParam("appPlatform","android")
     .queryParam("appType","SNAPLITE")
     .queryParam("start","${start}")
     .queryParam("count","10")
     .queryParam("adsPogCount","${adspgCnt}")
     .queryParam("dpPogCount","${dppgCnt}")
     .check(bodyString.saveAs("Response_Data"))
     )
      .exec(session => {
//      println("Some Restful Service: ")  
      writer.println("Body = " + session("Response_Data").as[String])
      session
      })
  }
    
    setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)   
     
}