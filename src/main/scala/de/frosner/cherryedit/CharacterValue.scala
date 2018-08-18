package de.frosner.cherryedit

sealed trait CharacterValue

case class StringValue(value: String) extends CharacterValue

case object Tombstone extends CharacterValue
