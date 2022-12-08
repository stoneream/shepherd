package html

object Attributes {
  def id(value: String) = Attribute.DoubleQuotedAttribute("id", value)
}
