package de.frosner.cherryedit

case class InsertAfterLocal(char: String, pos: Int)
case class DeleteLocal(pos: Int)

case class InsertAfterRemote(char: Character, identifier: Identifier)
case class DeleteRemote(identifier: Identifier)

case object RegisterClient
case object ClientInitialized
case class SetDocument(document: Document)
