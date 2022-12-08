package html

/**
 * HTML Standard : https://html.spec.whatwg.org/multipage/syntax.html#elements-2
 */

// addAttribute もっとどうにかならないものか...

sealed trait Element {
  val name: String
  val attributes: List[Attribute]
}

object Element {
  case class Void(name: String, attributes: List[Attribute]) extends Element {
    def addAttribute(attribute: Attribute): Void = copy(attributes = (attribute:: attributes).reverse)
  }

  case class EscapableRawText(name: String, attributes: List[Attribute], text: String) extends Element {
    def addAttribute(attribute: Attribute): EscapableRawText = copy(attributes = (attribute:: attributes).reverse)
  }

  case class Normal(name: String, attributes: List[Attribute], children: List[Element | Text]) extends Element {
    def addAttribute(attribute: Attribute): Normal = copy(attributes = (attribute:: attributes).reverse)

    def addChild(child: Element): Normal = copy(children = (child :: children).reverse)

    def addChild(child: Text): Normal = copy(children = (child :: children).reverse)
  }
}
