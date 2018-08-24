package de.frosner.cherryedit.core

import org.scalatest._

class IdentifierSpec extends FlatSpec with Matchers {

  "An identifier" should "compare by number first" in {
    Identifier("a", 1).compare(Identifier("b", 1)) should be(-1)
    Identifier("a", 2).compare(Identifier("b", 1)) should be(1)
    Identifier("a", 1).compare(Identifier("a", 1)) should be(0)
  }

}
