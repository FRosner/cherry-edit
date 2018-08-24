enablePlugins(JavaAppPackaging)
enablePlugins(GitVersioning)

lazy val projectName = "cherry-edit"

organization in ThisBuild := "de.frosner"

scalaVersion in ThisBuild := "2.12.4"

scalacOptions in ThisBuild := ScalacOptions.options

lazy val core = project
  .in(file("core"))
  .settings(
    name := projectName + "-core",
    libraryDependencies ++= List(Dependencies.scalaTest)
  )

lazy val server = project
  .in(file("server"))
  .settings(
    name := projectName + "-server",
    libraryDependencies ++= Dependencies.akka
  ).dependsOn(core)
  .enablePlugins(JavaAppPackaging)


lazy val client = project
  .in(file("client"))
  .settings(
    name := projectName + "-client",
    libraryDependencies ++= Dependencies.akka
  ).dependsOn(core)
  .enablePlugins(JavaAppPackaging)

lazy val root = project
  .in(file("."))
  .aggregate(client, server)