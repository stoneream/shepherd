import HTMLAttribute.DoubleQuotedAttribute

implicit class AttributeInterpolationString(private val sc: StringContext) extends AnyVal {
  def id(args: Any*): DoubleQuotedAttribute = DoubleQuotedAttribute("id", sc.parts.mkString)
  def cls(args: Any*): DoubleQuotedAttribute = DoubleQuotedAttribute("class", sc.parts.mkString)
}
