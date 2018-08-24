enablePlugins(JavaAppPackaging)

lazy val projectName = "cherry-edit"

version := "0.1"

scalaVersion := "2.12.4"

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

lazy val client = project
  .in(file("client"))
  .settings(
    name := projectName + "-client",
    libraryDependencies ++= Dependencies.akka
  ).dependsOn(core)

