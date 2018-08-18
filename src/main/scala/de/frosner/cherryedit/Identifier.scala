package de.frosner.cherryedit

import scala.math.Ordered.orderingToOrdered

case class Identifier(label: String, number: Int) extends Ordered[Identifier] {
  def compare(that: Identifier): Int = (this.number, this.label).compare((that.number, that.label))
}

object Identifier {
  lazy val empty = Identifier("emptyDoc", 0)
}
