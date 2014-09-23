package me.coocoon.msa

import akka.actor.Actor
import spray.http.MediaTypes._
import spray.routing.HttpService
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol


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

case class EMail2(var to:String,var from:String,var subject:String,var body:String)

object JsonImplicits extends DefaultJsonProtocol {
  implicit val impEMail = jsonFormat4(EMail2)

}

// this trait defines our service behavior independently from the service actor
trait RestService extends HttpService{

  import JsonImplicits._

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
          entity(as[EMail2]) { email =>
            // transfer to newly spawned actor
            detach() {
              //ctx => ctx.complete("Response")
              complete {
                // send email

                "email sent"+email.to

              }
            }
          }
        }
      }
    }
}
