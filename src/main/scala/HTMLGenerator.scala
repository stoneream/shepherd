object HTMLGenerator {
  def generate(tag: Tag, depth: Int = 0): String = {
    val indent = "  " * depth
    val attrsStr = tag.attrs
      .map { attr =>
        s"${attr.name}=\"${TextUtil.escape(attr.value)}\""
      }
      .mkString(" ")
    val startTag = s"$indent<${tag.name} $attrsStr>"
    val childrenStr = {
      val temp = tag.children.map(generate(_, depth + 1))
      if (temp.isEmpty) {
        temp.mkString("")
      } else {
        temp.mkString("\n")
      }
    }
    val endTag = s"$indent</${tag.name}>"

    (if (childrenStr.isEmpty) {
       Seq(startTag, endTag)
     } else {
       Seq(startTag, childrenStr, endTag)
     }).mkString("\n")
  }
}
