package de.frosner.cherryedit

case class Character(value: CharacterValue, identifier: Identifier) {
  def toPrintableString: String =
    value match {
      case StringValue(s) => if (s == "\0") "" else s
      case Tombstone      => ""
    }
}

object Character {
  lazy val empty = Character(StringValue("\0"), Identifier.empty)
}
