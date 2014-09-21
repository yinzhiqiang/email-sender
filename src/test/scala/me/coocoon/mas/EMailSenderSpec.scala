package me.coocoon.mas

import me.coocoon.msa.{EMail, EMailSender}
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
      val emailSender = new EMailSender(Option("user"),Option("password"))
      val body: String = "body"
      val subject: String = "subject"
      val email = new EMail("to@localhost.com", "from@localhost.com", subject, body)
      emailSender.send(email)

      Thread sleep 1000

      val momsInbox = Mailbox.get("to@localhost.com")
      momsInbox.size() mustEqual 1
      val momsMsg = momsInbox.get(0)

      momsMsg.getContentType  mustEqual "text/plain; charset=UTF-8"
      momsMsg.getContent mustEqual body
      momsMsg.getSubject mustEqual subject
    }

    "can cc text email" in {
      val emailSender = new EMailSender(Option("user"),Option("password"))
      val body: String = "body"
      val subject: String = "subject"
      val email = new EMail("to2@localhost.com", "from@localhost.com", subject, body)
      email.cc="cc@localhost.com"
      emailSender.send(email)


      Thread sleep 1000

      val momsInbox = Mailbox.get("cc@localhost.com")
      momsInbox.size() mustEqual 1
      val momsMsg = momsInbox.get(0)

      momsMsg.getContentType  mustEqual "text/plain; charset=UTF-8"
      momsMsg.getContent mustEqual body
      momsMsg.getSubject mustEqual subject
      momsMsg.getAllRecipients must haveSize(2)
    }

    "can bcc text email" in {
      val emailSender = new EMailSender(Option("user"),Option("password"))
      val body: String = "body"
      val subject: String = "subject"
      val email = new EMail("to2@localhost.com", "from@localhost.com", subject, body)
      email.bcc="cc@localhost.com"
      emailSender.send(email)


      Thread sleep 1000

      val momsInbox = Mailbox.get("bcc@localhost.com")
      momsInbox.size() mustEqual 1
      val momsMsg = momsInbox.get(0)

      momsMsg.getContentType  mustEqual "text/plain; charset=UTF-8"
      momsMsg.getContent mustEqual body
      momsMsg.getSubject mustEqual subject
    }

  }
}
