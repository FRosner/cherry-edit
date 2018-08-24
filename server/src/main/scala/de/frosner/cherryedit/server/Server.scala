package de.frosner.cherryedit.server

import akka.actor.{Actor, ActorRef}
import akka.event.{Logging, LoggingAdapter}
import de.frosner.cherryedit.core._

class Server(initialDocument: Document) extends Actor {

  val id: String = self.path.name
  val log: LoggingAdapter = Logging(context.system, this)
  private var document: Document = initialDocument
  private var clients: Set[ActorRef] = Set.empty

  def receive: PartialFunction[Any, Unit] = {
    case msg @ RegisterClient =>
      log.info(s"Client registered: ${sender().path.name}")
      clients += sender()
      sender() ! SetDocument(document)
    case msg @ InsertAfterRemote(char, pos) =>
      log.info(s"Before $msg: ${document.toPrintableString}")
      document = document.insertAfter(pos, char)
      clients.filter(_ != sender()).foreach(_ ! msg)
      log.info(s"After $msg: ${document.toPrintableString}")
    case msg @ DeleteRemote(identifier) =>
      log.info(s"Before $msg: ${document.toPrintableString}")
      clients.filter(_ != sender()).foreach(_ ! msg)
      log.info(s"After $msg: ${document.toPrintableString}")
    case unknown => log.info(s"Received unknown message: $unknown")
  }

}
