package me.coocoon.msa

import com.typesafe.config.ConfigFactory

/**
 * Created by zyin on 8/27/2014.
 */
object Configuration {
  private val config = ConfigFactory.load
  config.checkValid(ConfigFactory.defaultReference)

  val serverHost = config.getString("app.server.host")
  val serverPath = config.getString("app.server.path")
  val portHttp = config.getInt("app.server.ports.http")

  val mailServerHost = config.getString("app.email.server.host")
  val mailServerPort = config.getInt("app.email.server.port")
  val mailServerNeedAuth = config.getBoolean("app.email.server.auth")
  val mailServerNeedTtls = config.getBoolean("app.email.server.ttls")

}
