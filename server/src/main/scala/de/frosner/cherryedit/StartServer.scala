package de.frosner.cherryedit

import akka.actor.{ActorSystem, Props}

object StartServer extends App {

  println("Starting server")

  val system = ActorSystem("cherry-edit")

  val server = system.actorOf(Props(classOf[Server], Document.empty), "server")

  println("Server started")

}
