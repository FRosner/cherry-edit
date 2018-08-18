package de.frosner.cherryedit

import org.scalatest._

class DocumentSpec extends FlatSpec with Matchers {

  "A document" should "have an empty character at the beginning" in {
    val doc = Document.empty
    doc.characters should be(Seq(Character.empty))
    doc.highestIdentifierNumber should be(0)
  }

  it should "insert after a given position correctly" in {
    val value = "x"
    val label = "a"
    val (char, doc) = Document.empty.insertAfter(0, value, label)
    char should be(Character.empty)
    doc.characters should be(
      Seq(
        Character.empty,
        Character(StringValue(value), Identifier(label, 1))
      ))
    doc.highestIdentifierNumber should be(1)
  }

  it should "insert after a given identifier correctly (no conflict)" in {
    val newChar = Character(StringValue("x"), Identifier("a", 1))
    val doc = Document.empty.insertAfter(Character.empty.identifier, newChar)
    doc.characters should be(
      Seq(
        Character.empty,
        newChar
      ))
    doc.highestIdentifierNumber should be(1)
  }

  it should "insert after a given identifier correctly (conflict without probing)" in {
    val newChar1 = Character(StringValue("x"), Identifier("a", 1))
    val newChar2 = Character(StringValue("y"), Identifier("b", 1))
    val doc =
      Document.empty.insertAfter(Character.empty.identifier, newChar1).insertAfter(Character.empty.identifier, newChar2)
    doc.characters should be(
      Seq(
        Character.empty,
        newChar2,
        newChar1
      ))
    doc.highestIdentifierNumber should be(1)
  }

  it should "insert after a given identifier correctly (conflict with probing once)" in {
    val newChar1 = Character(StringValue("x"), Identifier("a", 1))
    val newChar2 = Character(StringValue("y"), Identifier("b", 1))
    val doc =
      Document.empty.insertAfter(Character.empty.identifier, newChar2).insertAfter(Character.empty.identifier, newChar1)
    doc.characters should be(
      Seq(
        Character.empty,
        newChar2,
        newChar1
      ))
    doc.highestIdentifierNumber should be(1)
  }

  it should "insert after a given identifier correctly (conflict with probing twice)" in {
    val newChar1 = Character(StringValue("x"), Identifier("a", 1))
    val newChar2 = Character(StringValue("y"), Identifier("b", 1))
    val newChar3 = Character(StringValue("z"), Identifier("b", 2))
    val doc =
      Document.empty
        .insertAfter(Character.empty.identifier, newChar2)
        .insertAfter(newChar2.identifier, newChar3)
        .insertAfter(Character.empty.identifier, newChar1)
    doc.characters should be(
      Seq(
        Character.empty,
        newChar2,
        newChar3,
        newChar1
      ))
    doc.highestIdentifierNumber should be(1)
  }

}
