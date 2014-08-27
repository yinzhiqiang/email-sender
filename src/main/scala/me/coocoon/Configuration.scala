package me.coocoon

import com.typesafe.config.ConfigFactory

/**
 * Created by zyin on 8/27/2014.
 */
object Configuration {
  private val config = ConfigFactory.load
  config.checkValid(ConfigFactory.defaultReference)

  val host = config.getString("app.server.host")
  val portHttp = config.getInt("app.server.ports.http")
}
