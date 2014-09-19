package me.coocoon.mas

import com.icegreen.greenmail.util.{ServerSetupTest, GreenMailUtil, GreenMail}
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

    "can be send text email" in {
      //var greenMail = new GreenMail(ServerSetupTest.SMTP) //uses test ports by default
      //greenMail.start()

      val emailSender = new EMailSender(Option("user"),Option("password"))
      val email = new EMail("to@localhost.com", "from@localhost.com", "subject", "body")
      emailSender.send(email)

      /*val messages = greenMail.getReceivedMessages()

      messages  must not be empty

      messages must have size(1)

      val text = GreenMailUtil.getBody(messages(0))
      greenMail.stop()
      text mustEqual "body"*/
      Thread sleep 50000

      val momsInbox = Mailbox.get("to@localhost.com")
      momsInbox.size === 1
      val momsMsg = momsInbox.get(0)
      momsMsg.getContent === "body"
      momsMsg.getSubject === "subject"


    }

  }
}
