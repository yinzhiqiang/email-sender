package me.coocoon

import akka.actor.Actor
import org.json4s.DefaultFormats
import spray.http.MediaTypes._
import spray.httpx.Json4sSupport
import spray.routing.HttpService

/**
 * Created by zyin on 8/27/2014.
 */
class EMailSenderServiceActor extends Actor with RestService  {
  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)


}

// this trait defines our service behavior independently from the service actor
trait RestService extends HttpService with Json4sSupport{
  override implicit def json4sFormats = DefaultFormats
  var test = ("test1","test2")
  val myRoute =
    path("") {
      get {
        respondWithMediaType(`application/json`) {
          complete {
           test
          }
        }
      }
    }
}
