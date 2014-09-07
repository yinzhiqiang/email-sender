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

}
