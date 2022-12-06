import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

@main def main(): Unit = {
  class Element(
      val id: String,
      val children: ArrayBuffer[Element] = ArrayBuffer()
  ) {
    def addChild(child: Element): Unit =
      children.append(child)
  }

  object Element {
    def apply(id: String)(child: Element ?=> Element)(using parent: Element): Element =
      given Element = new Element(id)

      given_Element.addChild(child)
      parent.addChild(given_Element)
      parent

    def apply(id: String)(using parent: Element): Element =
      given Element = new Element(id)
      parent.addChild(given_Element)
      given_Element
  }

  def f(using documentRoot: Element): Element = {
    Element("child-1") {
      Element("child-1-1")
      Element("child-1-2")
      Element("child-1-3")
    }
    Element("child-2") {
      Element("child-2-1")
      Element("child-2-2")
      Element("child-2-3")
    }
    Element("child-3") {
      Element("child-3-1")
      Element("child-3-2")
      Element("child-3-3")
    }
  }

  def show(parent: Element): Unit = {
    parent.children.foreach { child =>
      println(s"${child.id}")
      child.children.foreach { child =>
        println(s"${child.id}")
      }
    }
  }

  val a = f(using new Element("root"))

  show(a)

  /*
  child-1
  child-1-1
  child-1-2
  child-1-3
  child-1-3
  child-2
  child-2-1
  child-2-2
  child-2-3
  child-2-3
  child-3
  child-3-1
  child-3-2
  child-3-3
  child-3-3
  */
}
