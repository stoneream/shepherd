package html

/**
 * HTML Standard : https://html.spec.whatwg.org/multipage/syntax.html#attributes-2
 */

sealed trait Attribute {
  val value: String
}

object Attribute {
  case class EmptyAttribute(value: String) extends Attribute
  case class UnquotedAttribute(name: String, value: String) extends Attribute
  case class SingleQuotedAttribute(name: String, value: String) extends Attribute
  case class DoubleQuotedAttribute(name: String, value: String) extends Attribute
}
