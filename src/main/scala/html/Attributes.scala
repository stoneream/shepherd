package html

object Attributes {
  def id(value: String): Attribute.DoubleQuotedAttribute = Attribute.DoubleQuotedAttribute("id", value)

  def clz(value: String): Attribute.DoubleQuotedAttribute = Attribute.DoubleQuotedAttribute("class", value)
}
