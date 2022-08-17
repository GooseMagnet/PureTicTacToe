ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

libraryDependencies += "org.typelevel" %% "cats-effect" % "3.3.14" withSources() withJavadoc()

lazy val root = (project in file("."))
  .settings(
    name := "TicTacToe"
  )

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:postfixOps"
)
