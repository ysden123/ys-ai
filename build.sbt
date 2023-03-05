ThisBuild / scalaVersion := "3.2.1"
ThisBuild / version := "0.0.1"
ThisBuild / organization := "com.stulsoft"
ThisBuild / organizationName := "stulsoft"

lazy val json4sVersion = "4.0.6"

lazy val root = (project in file("."))
  .settings(
    name := "ys-ai",
    libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.4.5",
    libraryDependencies += "org.apache.httpcomponents.client5" % "httpclient5" % "5.2.1",
      libraryDependencies += "org.json4s" %% "json4s-native" % json4sVersion,
      libraryDependencies += "org.json4s" %% "json4s-jackson" % json4sVersion,

      libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test
  )