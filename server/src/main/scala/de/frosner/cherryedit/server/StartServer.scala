package de.frosner.cherryedit.server

import akka.actor.{ActorSystem, Props}
import de.frosner.cherryedit.core.Document

object StartServer extends App {

  println("Starting server")

  val system = ActorSystem("cherry-edit")

  val server = system.actorOf(Props(classOf[Server], Document.empty), "server")

  println("Server started")

}
