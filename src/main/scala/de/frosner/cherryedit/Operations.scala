package de.frosner.cherryedit

case class InsertAfterLocal(char: String, pos: Int)
case object InsertAfterLocalSuccess

case class DeleteLocal(pos: Int)

case class InsertAfterRemote(char: Character, identifier: Identifier)
case class DeleteRemote(identifier: Identifier)

case object RegisterClient
case object ClientInitialized
case object ClientNotInitialized
case class SetDocument(document: Document)
