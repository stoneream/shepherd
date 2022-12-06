sealed trait Text {
  val value: String
}

object Text {
  case class RawText(value: String) extends Text
  case class EscapableText(value: String) extends Text
}
