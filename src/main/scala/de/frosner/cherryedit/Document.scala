package de.frosner.cherryedit

case class Document(characters: Vector[Character], highestIdentifierNumber: Int) {

  private def insertAfter(pos: Int, newChar: Character): (Character, Document) = {
    val oldChar = characters(pos)
    val (head, tail) = characters.splitAt(pos + 1)
    val newDocument =
      Document(head ++ Vector(newChar) ++ tail, newChar.identifier.number)
    (oldChar, newDocument)
  }

  def insertAfter(pos: Int, char: String, label: String): (Character, Document) =
    insertAfter(pos, Character(StringValue(char), Identifier(label, highestIdentifierNumber + 1)))

  def insertAfter(identifier: Identifier, char: Character): Document = {
    val initialIndex = characters.indexWhere(_.identifier == identifier)
    if (initialIndex >= 0) {
      val realIndex = characters.indexWhere(_.identifier.compare(char.identifier) < 0, initialIndex + 1)
      if (realIndex > 0) {
        // resolve conflict by letting the higher node ID win and go first for concurrent edits on the same position
        val (_, document) = insertAfter(realIndex - 1, char)
        document
      } else {
        // we're inserting at the end
        val (_, document) = insertAfter(characters.length - 1, char)
        document
      }
    } else {
      throw new Exception(s"Identifier $identifier not found in document $this. Probably a message got lost inbetween?")
    }
  }

  def deleteAt(pos: Int): (Character, Document) = {
    val toDelete = characters(pos)
    val deletedChar = toDelete.copy(value = Tombstone)
    val newDocument = this.copy(characters = characters.updated(pos, deletedChar))
    (deletedChar, newDocument)
  }

  def delete(identifier: Identifier): Document = {
    val index = characters.indexWhere(_.identifier == identifier)
    this.copy(characters = characters.updated(index, characters(index).copy(value = Tombstone)))
  }

  def toPrintableString: String = characters.map(_.toPrintableString).mkString("")

}

object Document {
  lazy val empty: Document =
    Document(Vector(Character.empty), 0)
}
