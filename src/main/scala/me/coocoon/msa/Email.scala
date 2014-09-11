package me.coocoon.msa

import javax.mail.internet.InternetAddress

import me.coocoon.msa.EmailType.EmailType

/**
 * Created by zyin on 9/8/14.
 */
class Email(val to:String,val from:String,val subject:String,val body:String) {

  private var ccAddresss:Option[String] = None
  private var bccAddresss:Option[String] = None
  private var username:Option[String] = None
  private var paasword:Option[String] = None
  private var eamilType:EmailType = EmailType.TEXT

  def cc()= ccAddresss
  def cc_$eq(cc:String): Unit ={
     ccAddresss = cc
  }

  def bcc()= bccAddresss


}


