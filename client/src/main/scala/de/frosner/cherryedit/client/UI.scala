package de.frosner.cherryedit.client

import akka.actor.{Actor, ActorRef}
import akka.event.{Logging, LoggingAdapter}
import de.frosner.cherryedit.core._

class UI(client: ActorRef) extends Actor {

  val log: LoggingAdapter = Logging(context.system, this)
  client ! RegisterUI

  def receive: PartialFunction[Any, Unit] = {
    case msg: InsertAfterLocal =>
      log.info(s"Received: $msg")
      client ! msg
    case msg: DeleteLocal =>
      log.info(s"Received: $msg")
      client ! msg
    case PrintDocument(document) =>
      UI.show(document)
    case unknown => log.info(s"Received unknown message: $unknown")
  }

}

object UI {

  def show(document: Document): Unit = {
    Runtime.getRuntime.exec("clear")
    println(document.toPrintableString)
  }

}
