import scala.annotation.{tailrec, targetName}

@main def main(): Unit =
  val tag =
    div id "parent" $ (
      div id "child 1" $ (
        div id "child1-1",
        div id "child1-2"
      ),
      div id "child 2" $ (
        div id "child2-1"
      )
    )

  val html = HTMLGenerator.generate(tag)

  println(html)

  /*
  ホントはインデントで表現できたら良いのですが...?（もしかしてできる？
  div id "parent"
    div id "child1"
      div id "child1-1"
      div id "child1-2"
    div id "child2"
      div id "child2-1"
   */

case class Tag(name: String, attrs: Seq[Attr] = Nil, children: Seq[Tag] = Nil):
  def addAttr(attr: Attr): Tag = this.copy(attrs = attrs :+ attr)
  def addChild(child: Tag): Tag = this.copy(children = children :+ child)

extension (parent: Tag)
  @targetName("addChild")
  def $(children: Tag*): Tag = children.foldLeft(parent) { case (p, child) => p.addChild(child) }
  def id(name: String): Tag = parent.addAttr(IdAttr(name))
  def cls(name: String): Tag = parent.addAttr(ClassAttr(name))

trait Attr:
  val name: String
  val value: String

case class IdAttr(value: String) extends Attr:
  val name = "id"

case class ClassAttr(value: String) extends Attr:
  val name = "class"

val div = Tag("div")
