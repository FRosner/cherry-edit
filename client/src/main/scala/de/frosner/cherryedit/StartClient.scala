package de.frosner.cherryedit

import akka.actor.{ActorSystem, Props}

object StartClient extends App {

  val serverAddress = args(0)
  val system = ActorSystem("cherry-edit")

  val client1 = system.actorOf(Props(classOf[Client], s"${serverAddress}/user/server"), "a")

  Thread.sleep(1000)
  client1 ! InsertAfterLocal("a", 0)

  Thread.sleep(1000)
  client1 ! InsertAfterLocal("b", 1)

  Thread.sleep(1000)
  client1 ! DeleteLocal(1)

}
