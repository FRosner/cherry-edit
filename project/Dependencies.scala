import sbt._

object Dependencies {

  object Versions {
    val scalaTest = "3.0.1"
    val akka = "2.5.14"
  }

  val scalaTest: ModuleID = "org.scalatest" %% "scalatest" % Versions.scalaTest

  val akka: Seq[ModuleID] = List(
    "com.typesafe.akka" %% "akka-actor",
    "com.typesafe.akka" %% "akka-remote"
  ).map(_ % Versions.akka)

}
