object TextUtil {
  def escape(s: String): String =
    s.foldLeft("") { case (b, c) =>
      b + (c match {
        case '<' =>
          "&lt;"
        case '>' =>
          "&gt;"
        case '&' =>
          "&amp;"
        case '"' =>
          "&quote;"
        case '\'' =>
          "&#x27;"
        case char if char.isWhitespace =>
          ""
        case _ => s"$c"
      })
    }
}
