ThisBuild / scalaVersion := "2.13.6"
ThisBuild / organization := "net.toddsarratt"

lazy val neural-network = (project in file("."))
  .settings(
    name := "neural-network",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.12" % Test
  )