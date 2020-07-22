name := "daily-catalog"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(ws,
  filters,
  "org.json4s" %% "json4s-jackson" % "3.3.0",
  "org.json4s" %% "json4s-ext" % "3.3.0",
  "commons-io" % "commons-io" % "2.5",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.3.16",
  "com.github.wnameless" % "json-flattener" % "0.4.0",
  "com.amazonaws" % "aws-java-sdk-ssm" % "1.11.98",
  "io.swagger" %% "swagger-scala-module" % "1.0.3"
)


lazy val root = (project in file(".")).enablePlugins(PlayScala)
        