// https://html.spec.whatwg.org/multipage/syntax.html#attributes-2
sealed trait HTMLAttribute {
  val value: String
}

object HTMLAttribute {
  trait EmptyAttribute
  case class UnquotedAttribute(name: String, value: String) extends HTMLAttribute
  case class SingleQuotedAttribute(name: String, value: String) extends HTMLAttribute
  case class DoubleQuotedAttribute(name: String, value: String) extends HTMLAttribute
}

// todo validateion
