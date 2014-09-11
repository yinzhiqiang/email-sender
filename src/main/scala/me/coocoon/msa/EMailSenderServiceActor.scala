package me.coocoon.msa

import akka.actor.Actor
import org.json4s.DefaultFormats
import shapeless.get
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
trait RestService extends HttpService{
 // with Json4sSupport
 // override implicit def json4sFormats = DefaultFormats
  var test = ("test1","test2")
  val myRoute =
    path("es") {
      get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Semail-sender!</h1>
              </body>
            </html>
          }
        }
      }~
      post {
        // decompresses the request with Gzip or Deflate when required
        decompressRequest() {
          // unmarshal with in-scope unmarshaller
          entity(as[String]) { order =>
            // transfer to newly spawned actor
            detach() {
              complete {
                // ... write order to DB
                "Order received"
              }
            }
          }
        }
      }
    }
}
