package de.frosner.cherryedit

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask

import scala.concurrent.Await
import scala.concurrent.duration._

object Main extends App {

  val system = ActorSystem("cherry-edit")

  val server = system.actorOf(Props(classOf[Server], Document.empty), "server")

  val client1 = system.actorOf(Props(classOf[Client], server), "a")
  val client2 = system.actorOf(Props(classOf[Client], server), "b")

  Await.result((client1 ? InsertAfterLocal("a", 0))(5.seconds), 5.seconds) match {
    case InsertAfterLocalSuccess => println("Insert successful!")
    case ClientNotInitialized    => println("Client not initialized!")
  }
  Thread.sleep(1000)
  client1 ! InsertAfterLocal("a", 0)
  client1 ! InsertAfterLocal("b", 1)
  client2 ! InsertAfterLocal("x", 0)

}
