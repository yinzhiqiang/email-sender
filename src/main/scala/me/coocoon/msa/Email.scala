package me.coocoon.msa

import me.coocoon.msa.EmailType.EmailType

/**
 * Created by zyin on 9/8/14.
 */
case class EMail(val to:String,val from:String,val subject:String,val body:String, val cc:String,val bcc:String,val userName:String,val passWord:String,val emailType:String){

}


