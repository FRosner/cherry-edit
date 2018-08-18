name := "cherry-edit"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= List(
  "com.typesafe.akka" %% "akka-actor" % "2.5.14"
)

libraryDependencies ++= List(
  "org.scalatest" %% "scalatest" % "3.0.1"
).map(_ % Test)


