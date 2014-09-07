name := """email-sender"""

version := "1.0-SNAPSHOT"

organization := "coocoon.me"

scalaVersion := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "softprops-maven" at "http://dl.bintray.com/content/softprops/maven"

libraryDependencies ++= {
  val akkaV = "2.3.0"
  val sprayV = "1.3.1"
  Seq(
    "io.spray" % "spray-can" % sprayV withSources(),
    "io.spray" % "spray-httpx" % sprayV withSources() ,
    "io.spray" % "spray-routing" % sprayV withSources() ,
    "io.spray" % "spray-routing" % sprayV withSources() ,
    "com.typesafe.akka" %% "akka-actor" % akkaV withSources() ,
    "org.json4s" %% "json4s-native" % "3.2.10" withSources() ,
    "me.lessis" %% "courier" % "0.1.3" withSources(),//https://github.com/softprops/courier
    "io.spray" % "spray-testkit" % sprayV % "test" ,
    "com.typesafe.akka" %% "akka-testkit" % akkaV % "test" ,
    "org.specs2" %% "specs2-core" % "2.3.7" % "test" 
  )
}

Revolver.settings

