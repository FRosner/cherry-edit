package de.frosner.cherryedit.core

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
    val expected = Document(characters = Vector(
                              Character.empty,
                              Character(StringValue(value), Identifier(label, 1))
                            ),
                            highestIdentifierNumber = 1)
    doc should be(expected)
  }

  it should "insert after a given identifier correctly (no conflict)" in {
    val newChar = Character(StringValue("x"), Identifier("a", 1))
    val doc = Document.empty.insertAfter(Character.empty.identifier, newChar)
    val expected = Document(characters = Vector(
                              Character.empty,
                              newChar
                            ),
                            highestIdentifierNumber = 1)
    doc should be(expected)
  }

  it should "insert after a given identifier correctly (conflict without probing)" in {
    val newChar1 = Character(StringValue("x"), Identifier("a", 1))
    val newChar2 = Character(StringValue("y"), Identifier("b", 1))
    val doc =
      Document.empty.insertAfter(Character.empty.identifier, newChar1).insertAfter(Character.empty.identifier, newChar2)
    val expected = Document(characters = Vector(
                              Character.empty,
                              newChar2,
                              newChar1
                            ),
                            highestIdentifierNumber = 1)
    doc should be(expected)
  }

  it should "insert after a given identifier correctly (conflict with probing once)" in {
    val newChar1 = Character(StringValue("x"), Identifier("a", 1))
    val newChar2 = Character(StringValue("y"), Identifier("b", 1))
    val doc =
      Document.empty.insertAfter(Character.empty.identifier, newChar2).insertAfter(Character.empty.identifier, newChar1)
    val expected = Document(characters = Vector(
                              Character.empty,
                              newChar2,
                              newChar1
                            ),
                            highestIdentifierNumber = 1)
    doc should be(expected)
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
    val expected = Document(characters = Vector(
                              Character.empty,
                              newChar2,
                              newChar3,
                              newChar1
                            ),
                            highestIdentifierNumber = 1)
    doc should be(expected)
  }

  it should "delete at a given position correctly" in {
    val x = Character(StringValue("x"), Identifier("a", 1))
    val y = Character(StringValue("y"), Identifier("b", 1))
    val z = Character(StringValue("z"), Identifier("b", 2))
    val deletedX = x.copy(value = Tombstone)
    val doc = Document(Vector(x, y, z), 2)
    val (resChar, resDoc) = doc.deleteAt(0)

    resChar should be(deletedX)
    resDoc should be(Document(Vector(deletedX, y, z), 2))
  }

  it should "delete a given identifier correctly" in {
    val x = Character(StringValue("x"), Identifier("a", 1))
    val y = Character(StringValue("y"), Identifier("b", 1))
    val z = Character(StringValue("z"), Identifier("b", 2))
    val deletedX = x.copy(value = Tombstone)
    val doc = Document(Vector(x, y, z), 2)
    val resDoc = doc.delete(x.identifier)

    resDoc should be(Document(Vector(deletedX, y, z), 2))
  }

  it should "be able to delete a given identifier twice" in {
    val x = Character(StringValue("x"), Identifier("a", 1))
    val y = Character(StringValue("y"), Identifier("b", 1))
    val z = Character(StringValue("z"), Identifier("b", 2))
    val deletedX = x.copy(value = Tombstone)
    val doc = Document(Vector(x, y, z), 2)
    val (_, intermediateDoc) = doc.deleteAt(0)
    val resDoc = intermediateDoc.delete(x.identifier)

    resDoc should be(Document(Vector(deletedX, y, z), 2))
  }

}
