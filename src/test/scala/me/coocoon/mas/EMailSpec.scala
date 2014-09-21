package me.coocoon.mas

import me.coocoon.msa.{EmailType, EMail}
import org.specs2.mutable.Specification

/**
 * Created by zyin on 9/15/14.
 */
class EMailSpec extends Specification {

  "EMail object" should {

    "can be created and get correct data" in {
      val to = "to@test.com"
      val from = "from@test.com"
      val subject = "subject"
      val body = "body"
      var email = new EMail(to,from,subject,body)

      email must beAnInstanceOf[EMail]
      email.to mustEqual to
      email.from mustEqual from
      email.subject mustEqual subject
      email.body mustEqual body

      //default value check
      email.cc must beNone
      email.bcc must beNone
      email.username must beNone
      email.paasword must beNone
      email.emailType mustEqual EmailType.TEXT

    }

    "can be updated and get modified data" in {
      var to = "to@test.com"
      var from = "from@test.com"
      var subject = "subject"
      var body = "body"
      var email = new EMail(to,from,subject,body)

      email must beAnInstanceOf[EMail]
      email.to mustEqual to
      email.from mustEqual from
      email.subject mustEqual subject
      email.body mustEqual body

      to = "to2@test.com"
      email.to=to
      email.to mustEqual to

      from = "from2@test.com"
      email.from=from
      email.from mustEqual from

      subject = "subject2@test.com"
      email.subject=subject
      email.subject mustEqual subject

      body = "email body"
      email.body=body
      email.body mustEqual body

      val cc: String = "cc@test.com"
      email.cc=cc
      email.cc.get mustEqual cc

      val bcc: String = "bcc@test.com"
      email.bcc=bcc
      email.bcc.get mustEqual bcc

      val username = "username"
      email.username=username
      email.username.get mustEqual username

      val paasword = "paasword"
      email.paasword=paasword
      email.paasword.get mustEqual paasword

      val emailType = EmailType.MULTIPART
      email.emailType=emailType
      email.emailType mustEqual emailType
    }

    "should get default value when set optional values to null" in {
      val to = "to@test.com"
      val from = "from@test.com"
      val subject = "subject"
      val body = "body"
      var email = new EMail(to,from,subject,body)

      email must beAnInstanceOf[EMail]
      email.to mustEqual to
      email.from mustEqual from
      email.subject mustEqual subject
      email.body mustEqual body

      //default value check
      email.cc=null
      email.cc must beNone

      email.bcc=null
      email.bcc must beNone

      email.username=null
      email.username must beNone

      email.paasword=null
      email.paasword must beNone

      email.emailType=null
      email.emailType mustEqual EmailType.TEXT

    }


  }


}
