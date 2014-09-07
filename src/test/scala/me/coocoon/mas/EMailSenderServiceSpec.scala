package me.coocoon.mas

import me.coocoon.msa.RestService
import org.json4s.JObject
import org.specs2.mutable.Specification
import spray.http.StatusCodes._
import spray.testkit.Specs2RouteTest

class EMailSenderServiceSpec extends Specification with Specs2RouteTest with RestService {
  def actorRefFactory = system
  
  "MyService" should {

    "return a greeting for GET requests to the root path" in {
      Get("/es") ~> myRoute ~> check {
        responseAs[JObject] must not beNull
      }
    }

    "leave GET requests to other paths unhandled" in {
      Get("/es/kermit") ~> myRoute ~> check {
        handled must beFalse
      }
    }



    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put("/es") ~> sealRoute(myRoute) ~> check {
        status === MethodNotAllowed
        //responseAs[String] === "HTTP method not allowed, supported methods: GET"
      }
    }
  }
}
