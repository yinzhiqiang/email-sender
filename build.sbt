name := """email-sender"""

version := "1.0-SNAPSHOT"

organization  := "coocoon.me"

scalaVersion  := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.0"
  val sprayV = "1.3.1"
  Seq(
    "io.spray"            %   "spray-can"     % sprayV withSources(),
    "io.spray"            %   "spray-routing" % sprayV withSources(),
    "io.spray"            %   "spray-testkit" % sprayV  % "test" withSources(),
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV withSources(),
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test" withSources(),
    "org.specs2"          %%  "specs2-core"   % "2.3.7" % "test" withSources()
  )
}

Revolver.settings
