case class UnescapeText(s: String)
type EscapableText = String

implicit class TextInterpolationString(private val sc: StringContext) extends AnyVal {
  def unescape(args: Any*): UnescapeText = UnescapeText(sc.parts.mkString)
}
