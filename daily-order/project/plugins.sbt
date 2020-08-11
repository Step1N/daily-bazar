logLevel := Level.Info

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "com.iheart" at "http://dl.bintray.com/iheartradio/sbt-plugins",
  "net.cakesolutions" at "https://dl.bintray.com/cakesolutions/maven",
  Resolver.bintrayRepo("cakesolutions", "maven"),
  Classpaths.sbtPluginReleases)

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.7.2")
addSbtPlugin("com.iheart" % "sbt-play-swagger" % "0.6.2-PLAY2.6")
