package me.coocoon

import akka.actor.Actor
import akka.actor.Actor.Receive
import spray.http.MediaTypes._
import spray.routing.HttpService

/**
 * Created by zyin on 8/27/2014.
 */
class EMailSenderServiceActor extends Actor with RestService {
  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}

// this trait defines our service behavior independently from the service actor
trait RestService extends HttpService {

  val myRoute =
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            <html>
              <body>
                <h1>Say hello to <i> Email Send Service</i> on <i>spray-can</i>!!!</h1>
              </body>
            </html>
          }
        }
      }
    }
}
