package me.coocoon.msa

import javax.mail.internet.InternetAddress

import courier._, Defaults._

/**
 * Created by zyin on 9/8/14.
 */
class EMailSender(username:Option[String],password:Option[String]) {



  def send(email:EMail): Unit ={
    var session = Mailer(Configuration.mailServerHost, Configuration.mailServerPort).auth(Configuration.mailServerNeedAuth).startTtls(Configuration.mailServerNeedTtls)


    if(Configuration.mailServerNeedAuth){
      if(username == None){
        throw new RuntimeException("User name can't none");
      }
      if(password == None){
        throw new RuntimeException("password can't none");
      }

      session = session.as(username.get, password.get)
    }

    val mailer = session()

    check(email)

    var envelope = Envelope.from(new InternetAddress(email.from))
      .to( convertToIntAddress(email.to):_*)
      .subject(email.subject)
    
    if(email.cc != None){
      envelope = envelope.cc(convertToIntAddress(email.cc.get):_*)
    }

    if(email.bcc != None){
      envelope = envelope.bcc(convertToIntAddress(email.bcc.get):_*)
    }

   email.emailType match {

     case EmailType.HTML => {  if(!email.body.isEmpty){
       envelope = envelope.content(Multipart().html(email.body))
     }}
     case EmailType.MULTIPART => {  if(!email.body.isEmpty){
       envelope = envelope.content(Multipart().html(email.body))
     }}
     case _ => {  if(!email.body.isEmpty){
       envelope = envelope.content(Text(email.body))
     }}

   }
    mailer(envelope).onSuccess {
      case _ => println("message delivered")
    }


  }

  def convertToIntAddress(toStr: String):Array[InternetAddress] = {
    val toArray = toStr.split(",")
    if(toArray.isEmpty){
      throw new RuntimeException("email address can't empty");
    }
    var toAddress:Array[InternetAddress] = new Array[InternetAddress](toArray.length)
    for(  i <- 0 to (toArray.length - 1)){
      toAddress(i) = new InternetAddress(toArray(i))
    }
    toAddress
  }

  def check(email: EMail) {
    if (email.from.isEmpty) {
      throw new RuntimeException("from can't empty");
    }

    if (email.to.isEmpty) {
      throw new RuntimeException("to can't empty");
    }

    if (email.subject.isEmpty) {
      throw new RuntimeException("subject can't empty");
    }
  }
}
