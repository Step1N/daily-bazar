logLevel := Level.Info

resolvers ++= Seq("Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  Classpaths.sbtPluginReleases)

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.7.2")