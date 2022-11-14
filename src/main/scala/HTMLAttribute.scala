// https://html.spec.whatwg.org/multipage/syntax.html#attributes-2
sealed trait HTMLAttribute {
  val value: String
}

object HTMLAttribute {
  trait EmptyAttribute
  trait UnquotedAttribute {
    val name: String
  }
  trait SingleQuotedAttribute {
    val name: String
  }
  trait DoubleQuotedAttribute {
    val name: String
  }
}

// todo validateion
