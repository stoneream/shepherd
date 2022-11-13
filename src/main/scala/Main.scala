import scala.annotation.{tailrec, targetName}

@main def main(): Unit =
  val dom =
    div id "parent" $ (
      div id "child 1" $ (
        div id "child1-1" $ (
          p("hello")
        ),
        div id "child1-2"
      ),
      div id "child 2" $ (
        div id "child2-1"
      )
    )

  println(dom.toHTML())

  /*
  ホントはインデントで表現できたら良いのですが...?（もしかしてできる？
  div id "parent"
    div id "child1"
      div id "child1-1"
      div id "child1-2"
    div id "child2"
      div id "child2-1"
   */

// 根本的に設計ミスったかも

case class Tag(name: String, attrs: Seq[Attr] = Nil, children: Seq[Tag | String] = Nil):
  def addAttr(attr: Attr): Tag = this.copy(attrs = attrs :+ attr)
  def addChild(child: Tag | String): Tag = this.copy(children = children :+ child)
  def toHTML(depth: Int = 0): String = {
    val indent = "  " * depth
    val attrsStr = attrs.map(_.toHTML).mkString(" ")
    val startTag = s"$indent<$name $attrsStr>"
    val childrenStr = {
      val temp = children.map {
        case str: String => TextUtil.escape(str)
        case tag: Tag => tag.toHTML(depth + 1)
      }
      if (temp.isEmpty) {
        temp.mkString("")
      } else {
        temp.mkString("\n")
      }
    }
    val endTag = s"$indent</$name>"

    (if (childrenStr.isEmpty) {
       Seq(startTag, endTag)
     } else {
       Seq(startTag, childrenStr, endTag)
     }).mkString("\n")
  }

extension (parent: Tag)
  def $(children: (Tag | String)*): Tag = children.foldLeft(parent) { case (p, child) => p.addChild(child) }
  def id(name: String): Tag = parent.addAttr(IdAttr(name))
  def cls(name: String): Tag = parent.addAttr(ClassAttr(name))

trait Attr:
  val name: String
  val value: String
  def toHTML: String = s"$name=\"${TextUtil.escape(value)}\""

case class IdAttr(value: String) extends Attr:
  val name = "id"

case class ClassAttr(value: String) extends Attr:
  val name = "class"

def div: Tag = Tag("div")
def p(paragraph: String): Tag = Tag("p").addChild(paragraph)
