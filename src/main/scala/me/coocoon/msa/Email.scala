package me.coocoon.msa

import me.coocoon.msa.EmailType.EmailType

/**
 * Created by zyin on 9/8/14.
 */
class EMail(var to:String,var from:String,var subject:String,var body:String) {

  private var cc$:Option[String] = None
  private var bcc$:Option[String] = None
  private var username$:Option[String] = None
  private var paasword$:Option[String] = None
  private var emailType$:EmailType = EmailType.TEXT

  def cc= cc$
  def cc_=(value: String) {
    value match {
      case value if !(value.isEmpty) =>  cc$ = Option(value)
      case _ => cc$ = None
    }
  }

  def bcc= bcc$
  def bcc_=(value: String) {
    value match {
      case value if !(value.isEmpty) =>  bcc$ = Option(value)
      case _ => bcc$ = None
    }
  }

  def username= username$
  def username_=(value: String) {
    value match {
      case value if !(value.isEmpty) =>  username$ = Option(value)
      case _ => username$ = None
    }
  }

  def paasword= paasword$
  def paasword_=(value: String) {
    value match {
      case value if !(value.isEmpty) =>  paasword$ = Option(value)
      case _ => paasword$ = None
    }
  }

  def emailType= emailType$
  def emailType_=(value: EmailType) {
    value match {
      case value if value != null =>  emailType$ = value
      case _ => emailType$ = EmailType.TEXT
    }
  }

}


