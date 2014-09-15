package me.coocoon.msa

import javax.mail.internet.InternetAddress

import courier._, Defaults._

/**
 * Created by zyin on 9/8/14.
 */
class EMailSender(username:String,password:String) {

  def send(email:EMail): Unit ={
    val mailer = Mailer(Configuration.mailServerHost, Configuration.mailServerPort)
      .auth(Configuration.mailServerNeedAuth)
      .as(username, password)
      .startTtls(Configuration.mailServerNeedTtls)()


    val envelope = Envelope.from(new InternetAddress(email.from))
      .to()
      .subject(email.subject)



    mailer(envelope).onSuccess {
      case _ => println("message delivered")
    }

    mailer(Envelope.from("you" `@` "work.com")
      .to("boss" `@` "work.com")
      .subject(email.subject)
      .content(Multipart()
      .attach(new java.io.File("tps.xls"))
      .html("<html><body><h1>IT'S IMPORTANT</h1></body></html>")))
      .onSuccess {
      case _ => println("delivered report")
    }

  }

}
