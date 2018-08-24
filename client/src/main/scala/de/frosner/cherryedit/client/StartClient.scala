package de.frosner.cherryedit.client

import akka.actor.{ActorSystem, Props}
import de.frosner.cherryedit.core.{DeleteLocal, InsertAfterLocal}

import scala.io.StdIn

object StartClient extends App {

  val serverAddress = args(0)
  val system = ActorSystem("cherry-edit")

  val client = system.actorOf(Props(classOf[Client], s"${serverAddress}/user/server"), "a")
  val ui = system.actorOf(Props(classOf[UI], client))

  var quit = false
  while (!quit) {
    try {
      val input = StdIn.readLine()
      if (input == "q") {
        quit = true
      } else {
        val Array(operation, value) = input.split(":")
        operation match {
          case "i" =>
            val Array(pos, char) = value.split(",")
            ui ! InsertAfterLocal(char, pos.toInt)
          case "d" => ui ! DeleteLocal(value.toInt)
        }
      }
    } catch {
      case _: Throwable => println(s"Illegal command.")
    }
  }

  system.terminate()

}
