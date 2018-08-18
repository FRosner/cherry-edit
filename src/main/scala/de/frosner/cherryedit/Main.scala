package de.frosner.cherryedit

import java.util.UUID

import akka.actor.{ActorSystem, Props}

object Main extends App {

  val system = ActorSystem("cherry-edit")

  val server = system.actorOf(Props(classOf[Server], Document.empty), "server")

  val client1 = system.actorOf(Props(classOf[Client], server), "a")
  val client2 = system.actorOf(Props(classOf[Client], server), "b")

  Thread.sleep(1000)
  client1 ! InsertAfterLocal("a", 0)
  client1 ! InsertAfterLocal("b", 1)
  client2 ! InsertAfterLocal("x", 0)

}
