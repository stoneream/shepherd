import scala.annotation.targetName

// https://momdo.github.io/html/syntax.html#elements-2
sealed trait HTMLElement {
  val name: String
  val attributes: List[HTMLAttribute]
}

object HTMLElement {
  type Child = HTMLElement | UnescapeText | EscapableText

  trait VoidElement extends HTMLElement

  trait EscapableRawTextElement extends HTMLElement {
    val text: EscapableText
  }

  class NormalElement(val name: String, val attributes: List[HTMLAttribute] = Nil, val children: List[Child] = Nil) extends HTMLElement {
    def addChild(child: Child): NormalElement = {
      NormalElement.apply(name = name, attributes = attributes, children = (child :: children).reverse)
    }
    def addChildren(children: Child*): NormalElement = {
      children.foldLeft(NormalElement.apply(name, attributes, this.children)) { case (acc, child) => acc.addChild(child) }
    }
    def addAttribute(attribute: HTMLAttribute): NormalElement = {
      NormalElement.apply(name = name, attributes = (attribute :: attributes).reverse, children = children)
    }
    def addAttributes(attributes: HTMLAttribute*): NormalElement = {
      attributes.foldLeft(NormalElement.apply(name, this.attributes, children)) { case (acc, attribute) => acc.addAttribute(attribute) }
    }
  }
  object NormalElement {
    def apply(name: String, attributes: List[HTMLAttribute] = Nil, children: List[Child] = Nil) = new NormalElement(name, attributes, children)
  }

  extension (normalElement: NormalElement)
    @targetName("addChildren")
    def /(children: Child*): NormalElement = normalElement.addChildren(children: _*)
    @targetName("addAttributes")
    def ~(attributes: HTMLAttribute*) = normalElement.addAttributes(attributes: _*)
}

// todo validateion
