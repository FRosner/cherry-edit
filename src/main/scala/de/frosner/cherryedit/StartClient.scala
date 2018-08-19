package de.frosner.cherryedit

import java.io.File

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import de.frosner.cherryedit.StartServer.getClass

object StartClient extends App {

  val configFile = getClass.getClassLoader.getResource("client_application.conf").getFile
  val config = ConfigFactory.parseFile(new File(configFile))

  val serverAddress = args(0)
  val system = ActorSystem("cherry-edit-client", config)

  val client1 = system.actorOf(Props(classOf[Client], s"${serverAddress}/user/server"), "a")
  val client2 = system.actorOf(Props(classOf[Client], s"${serverAddress}/user/server"), "b")

  Thread.sleep(1000)
  client1 ! InsertAfterLocal("a", 0)
  client1 ! InsertAfterLocal("b", 1)
  client2 ! InsertAfterLocal("x", 0)

}
