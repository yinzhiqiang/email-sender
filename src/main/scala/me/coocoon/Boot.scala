package me.coocoon

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

/**
 * Created by zyin on 8/27/2014.
 */
object Boot extends App {

// we need an ActorSystem to host our application in
implicit val system = ActorSystem("on-spray-can")

// create and start our service actor
val service = system.actorOf(Props[EMailSenderServiceActor], "email-sender-service")

implicit val timeout = Timeout(5.seconds)
// start a new HTTP server with our service actor as the handler
IO(Http) ? Http.Bind(service, interface = Configuration.serverHost, port = Configuration.portHttp)
}
