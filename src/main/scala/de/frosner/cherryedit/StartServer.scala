package de.frosner.cherryedit

import java.io.File

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object StartServer extends App {

  val configFile = getClass.getClassLoader.getResource("server_application.conf").getFile
  val config = ConfigFactory.parseFile(new File(configFile))

  val system = ActorSystem("cherry-edit-server", config)

  val server = system.actorOf(Props(classOf[Server], Document.empty), "server")

}
