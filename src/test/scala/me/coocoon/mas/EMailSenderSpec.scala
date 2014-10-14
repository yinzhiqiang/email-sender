package me.coocoon.mas

import me.coocoon.msa.{EMail, SMTPEMailSender}
import org.jvnet.mock_javamail.Mailbox
import org.specs2.mutable.Specification
import org.specs2.time.NoTimeConversions

import scala.concurrent.duration._
import scala.concurrent.Await

/**
 * Created by zyin on 9/19/14.
 */
class EMailSenderSpec extends Specification  with NoTimeConversions {


  "EMail sender " should {

    "can  send text email" in {
      val emailSender = new SMTPEMailSender(Option("user"),Option("password"))
      val body: String = "body"
      val subject: String = "subject"
      val email = new EMail("to@localhost.com", "from@localhost.com", subject, body,null,null,null,null,null)
      emailSender.send(email)

      //Thread sleep 1000

      val momsInbox = Mailbox.get("to@localhost.com")
      momsInbox.size() mustEqual 1
      val momsMsg = momsInbox.get(0)

      momsMsg.getContentType  mustEqual "text/plain; charset=UTF-8"
      momsMsg.getContent mustEqual body
      momsMsg.getSubject mustEqual subject
    }

    "can cc text email" in {
      val emailSender = new SMTPEMailSender(Option("user"),Option("password"))
      val body: String = "body"
      val subject: String = "subject"
      val cc: String = "cc@localhost.com"
      val email = new EMail("to2@localhost.com", "from@localhost.com", subject,body, cc,null,null,null,null)
      emailSender.send(email)

     // Thread sleep 1000

      val momsInbox = Mailbox.get(cc)
      momsInbox.size() mustEqual 1
      val momsMsg = momsInbox.get(0)

      momsMsg.getContentType  mustEqual "text/plain; charset=UTF-8"
      momsMsg.getContent mustEqual body
      momsMsg.getSubject mustEqual subject
      momsMsg.getAllRecipients must haveSize(2)
    }

    "can bcc text email" in {
      val emailSender = new SMTPEMailSender(Option("user"),Option("password"))
      val body: String = "body"
      val subject: String = "subject"
      val bcc: String = "bcc@localhost.com"
      val email =  EMail("to2@localhost.com", "from@localhost.com", subject, body,null,bcc,null,null,null)
      emailSender.send(email)

      //Thread sleep 1000

      val momsInbox = Mailbox.get(bcc)
      momsInbox.size() mustEqual 1
      val momsMsg = momsInbox.get(0)

      momsMsg.getContentType  mustEqual "text/plain; charset=UTF-8"
      momsMsg.getContent mustEqual body
      momsMsg.getSubject mustEqual subject
    }

  }
}
