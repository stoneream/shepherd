
@main def main(): Unit =
  val v =
    div id "parent" $ (
      div id "child1" $ (
        div id "child1-1",
        div id "child1-2"
      ),
      div id "child2" $ (
        div id "child2-2"
      )
    )

  println(v)

case class Tag(name: String, attrs: Seq[Attr] = Nil, children: Seq[Tag] = Nil):
  def addAttr(attr: Attr): Tag = this.copy(attrs = attrs :+ attr)
  def addChild(child: Tag): Tag = this.copy(children = children :+ child)

extension (parent: Tag)
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
