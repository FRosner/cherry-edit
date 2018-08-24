package de.frosner.cherryedit.core

case class Character(value: CharacterValue, identifier: Identifier) {
  def toPrintableString: String =
    value match {
      case StringValue(s) => if (s == Character.emptyCharacterString) "" else s
      case Tombstone      => ""
    }
}

object Character {
  private val emptyCharacterString = '\u0000'.toString
  lazy val empty = Character(StringValue(emptyCharacterString), Identifier.empty)
}
