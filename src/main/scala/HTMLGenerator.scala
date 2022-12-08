//import scala.annotation.tailrec

//object HTMLGenerator {
//  def generate(child: Child, depth: Int = 0): String = {
//    val indent = "  " * depth
//    child match {
//      case element: HTMLElement.NormalElement =>
//        val attrsStr = element.attributes
//          .map {
//            case HTMLAttribute.DoubleQuotedAttribute(name, value) =>
//              s"$name=\"${TextUtil.escape(value)}\""
//            case HTMLAttribute.SingleQuotedAttribute(name, value) => ???
//            case HTMLAttribute.UnquotedAttribute(name, value) => ???
//          }
//          .mkString(" ")
//
//        val startTag = s"$indent<${element.name} $attrsStr>"
//        val endTag = s"$indent</${element.name}>"
//        val childrenStr = {
//          val temp = element.children.map {
//            case UnescapeText(s) =>
//              generate(s, depth + 1)
//            case s: EscapableText =>
//              generate(s, depth + 1)
//            case childElement: HTMLElement =>
//              generate(childElement, depth + 1)
//          }
//          if (temp.isEmpty) {
//            temp.mkString("")
//          } else {
//            temp.mkString("\n")
//          }
//        }
//        (if (childrenStr.isEmpty) {
//           Seq(startTag, endTag)
//         } else {
//           Seq(startTag, childrenStr, endTag)
//         }).mkString("\n")
//      case element: HTMLElement.VoidElement =>
//        ???
//      case element: HTMLElement.EscapableRawTextElement =>
//        ???
//      case UnescapeText(s) => s"$indent$s"
//      case s: EscapableText => s"$indent${TextUtil.escape(s)}"
//    }
//  }
//}
