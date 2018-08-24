package de.frosner.cherryedit

import java.io.File

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object StartServer extends App {

  println("Starting server")

  val system = ActorSystem("cherry-edit")

  val server = system.actorOf(Props(classOf[Server], Document.empty), "server")

  println("Server started")

}
