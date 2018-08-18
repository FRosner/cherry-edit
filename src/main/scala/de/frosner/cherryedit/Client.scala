package de.frosner.cherryedit

import java.util.UUID

import akka.actor.{Actor, ActorRef}
import akka.event.{Logging, LoggingAdapter}

// TODO look up server with Akka cluster later (https://doc.akka.io/docs/akka/1.3.1/scala/tutorial-chat-server.html)
class Client(server: ActorRef) extends Actor {

  val id: String = self.path.name
  val log: LoggingAdapter = Logging(context.system, this)
  private var document: Option[Document] = None

  server ! RegisterClient

  def receive: PartialFunction[Any, Unit] = {
    case msg @ InsertAfterLocal(char, pos) =>
      log.info(s"Before $msg: ${document.map(_.toPrintableString).getOrElse("")}")
      document = document.map { doc =>
        val (oldChar, newDoc) = doc.insertAfter(pos, char, id)
        val newPos = pos + 1
        server ! InsertAfterRemote(newDoc.characters(newPos), oldChar.identifier)
        newDoc
      }
      log.info(s"After $msg: ${document.map(_.toPrintableString).getOrElse("")}")
    case msg @ InsertAfterRemote(char, identifier) =>
      log.info(s"Before $msg: ${document.map(_.toPrintableString).getOrElse("")}")
      document = document.map(_.insertAfter(identifier, char))
      log.info(s"After $msg: ${document.map(_.toPrintableString).getOrElse("")}")
    case msg @ DeleteRemote(identifier) =>
      log.info(s"Before $msg: ${document.map(_.toPrintableString).getOrElse("")}")
      log.info(s"After $msg: ${document.map(_.toPrintableString).getOrElse("")}")
    case msg @ DeleteLocal(pos) =>
      log.info(s"Before $msg: ${document.map(_.toPrintableString).getOrElse("")}")
      log.info(s"After $msg: ${document.map(_.toPrintableString).getOrElse("")}")
    case msg @ SetDocument(newDocument) =>
      log.info(s"Before $msg: ${document.map(_.toPrintableString).getOrElse("")}")
      document = Some(newDocument)
      log.info(s"After $msg: ${document.map(_.toPrintableString).getOrElse("")}")
    case unknown => log.info(s"Received unknown message: $unknown")
  }

}