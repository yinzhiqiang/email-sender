package me.coocoon.msa

import me.coocoon.msa.EmailType.EmailType

/**
 * Created by zyin on 9/8/14.
 */
class EMail(var to:String,var from:String,var subject:String,var body:String) {

  private var _cc:Option[String] = None
  private var _bcc:Option[String] = None
  private var _username:Option[String] = None
  private var _paasword:Option[String] = None
  private var _emailType:EmailType = EmailType.TEXT

  def cc= _cc
  def cc_=(value: String) {
    value match {
      case value if !(value.isEmpty) =>  _cc = Option(value)
      case _ => _cc = None
    }
  }

  def bcc= _bcc
  def bcc_=(value: String) {
    value match {
      case value if !(value.isEmpty) =>  _bcc = Option(value)
      case _ => _bcc = None
    }
  }

  def username= _username
  def username_=(value: String) {
    value match {
      case value if !(value.isEmpty) =>  _username = Option(value)
      case _ => _username = None
    }
  }

  def paasword= _paasword
  def paasword_=(value: String) {
    value match {
      case value if !(value.isEmpty) =>  _paasword = Option(value)
      case _ => _paasword = None
    }
  }

  def emailType= _emailType
  def emailType_=(value: EmailType) {
    value match {
      case value if value != null =>  _emailType = value
      case _ => _emailType = EmailType.TEXT
    }
  }

}


