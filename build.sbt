// set the name of the project
name := "Tfl-Scala"

version := "0.1"

organization := "uk.co.bocuma"


// set the Scala version used for the project
scalaVersion := "2.11.1"


resolvers ++= Seq(
    "Sonatype OSS Releases"  at "http://oss.sonatype.org/content/repositories/releases/"
  )

// add compile dependencies on some dispatch modules
libraryDependencies ++= Seq(
    "net.ceedubs" %% "ficus" % "1.1.1",
    "org.json4s" %% "json4s-jackson" % "3.2.10",
    "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
    "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
    "co.freeside" % "betamax" % "1.1.2" % "test",
    "org.codehaus.groovy" % "groovy-all" % "1.8.8" % "compile"

)
