name := "daily-order"

version := "0.1"

scalaVersion := "2.12.6"
crossScalaVersions := Seq("2.11.12", "2.12.10", "2.13.1")
libraryDependencies ++= Seq(ws,
  filters,
  guice,
  "org.json4s" %% "json4s-jackson" % "3.5.3",
  "org.json4s" %% "json4s-ext" % "3.6.7",
  "commons-io" % "commons-io" % "2.5",
  "com.typesafe.akka" %% "akka-actor" % "2.5.20",
  "com.github.wnameless" % "json-flattener" % "0.4.0",
  "com.amazonaws" % "aws-java-sdk-ssm" % "1.11.98",
  "io.swagger" %% "swagger-play2" % "1.6.0",
  "org.apache.kafka" %% "kafka" % "2.1.0",
  "org.apache.poi" % "poi" % "3.17",
  "org.apache.poi" % "poi-ooxml" % "3.17",
  "org.apache.poi" % "poi-ooxml-schemas" % "3.17",
  "org.webjars" % "swagger-ui" % "2.2.0"
)
swaggerDomainNameSpaces := Seq("models")

lazy val root = (project in file(".")).enablePlugins(PlayScala, SwaggerPlugin)
        