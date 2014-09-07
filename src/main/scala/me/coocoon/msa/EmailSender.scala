package me.coocoon.msa

import courier._, Defaults._

/**
 * Created by zyin on 9/8/14.
 */
class EmailSender(username:String,password:String) {

  def send(): Unit ={
    val mailer = Mailer(Configuration.mailServerHost, Configuration.mailServerPort)
      .auth(Configuration.mailServerNeedAuth)
      .as(username, password)
      .startTtls(Configuration.mailServerNeedTtls)()

    mailer(Envelope.from("you" `@` "gmail.com")
      .to("mom" `@` "gmail.com")
      .cc("dad" `@` "gmail.com")
      .subject("miss you")
      .content(Text("hi mom"))).onSuccess {
      case _ => println("message delivered")
    }

    mailer(Envelope.from("you" `@` "work.com")
      .to("boss" `@` "work.com")
      .subject("tps report")
      .content(Multipart()
      .attach(new java.io.File("tps.xls"))
      .html("<html><body><h1>IT'S IMPORTANT</h1></body></html>")))
      .onSuccess {
      case _ => println("delivered report")
    }


  }

}
