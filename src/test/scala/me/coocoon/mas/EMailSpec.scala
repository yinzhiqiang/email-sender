package me.coocoon.mas

import me.coocoon.msa.{EmailType, EMail}
import org.specs2.mutable.Specification

/**
 * Created by zyin on 9/15/14.
 */
class EMailSpec extends Specification {

  "EMail object" should {

    "can be created and get correct data  with optional values set to null" in {
      val to = "to@test.com"
      val from = "from@test.com"
      val subject = "subject"
      val body = "body"
      var email = EMail(to,from,subject,body,null,null,null,null,null)

      email must beAnInstanceOf[EMail]
      email.to mustEqual to
      email.from mustEqual from
      email.subject mustEqual subject
      email.body mustEqual body

      //optional value check
      email.cc must beNull
      email.bcc must beNull
      email.userName must beNull
      email.passWord must beNull
      email.emailType must beNull
    }

    "can be created and get correct data with optional values not set to null" in {
      val to = "to@test.com"
      val from = "from@test.com"
      val subject = "subject"
      val body = "body"
      val cc ="cc@test.com"
      val bcc ="bcc@test.com"
      val userName ="userName"
      val passWord ="passWord"
      val emailType = "emailType"
      var email = new EMail(to,from,subject,body,cc,bcc,userName,passWord,emailType)

      email must beAnInstanceOf[EMail]
      email.to mustEqual to
      email.from mustEqual from
      email.subject mustEqual subject
      email.body mustEqual body

      //optional value check
      email.cc mustEqual cc
      email.bcc mustEqual bcc
      email.userName mustEqual userName
      email.passWord mustEqual passWord
      email.emailType mustEqual emailType
    }


  }


}
