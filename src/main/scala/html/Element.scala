package html

import scala.annotation.targetName

/**
 * HTML Standard : https://html.spec.whatwg.org/multipage/syntax.html#elements-2
 */

// addAttribute もっとどうにかならないものか...
// children/attributesについて。Listは末尾への追加の計算量が線形に増える、ので、reverseして先頭に追加して再度reverse

sealed trait Element {
  val name: String
  val attributes: List[Attribute]
}

object Element {
  case class Void(name: String, attributes: List[Attribute]) extends Element {
    def addAttribute(attribute: Attribute): Void = copy(attributes = (attribute :: attributes.reverse).reverse)

    @targetName("addAttribute(Void)")
    def :=(attribute: Attribute): Void = addAttribute(attribute)
  }

  case class EscapableRawText(name: String, attributes: List[Attribute], text: String) extends Element {
    def addAttribute(attribute: Attribute): EscapableRawText = copy(attributes = (attribute :: attributes.reverse).reverse)

    @targetName("addAttribute(EscapableRawText)")
    def :=(attribute: Attribute): EscapableRawText = addAttribute(attribute)
  }

  case class Normal(name: String, attributes: List[Attribute], children: List[Element | Text]) extends Element {
    def addAttribute(attribute: Attribute): Normal = copy(attributes = (attribute :: attributes.reverse).reverse)

    def addChild(child: Element): Normal = copy(children = (child :: children.reverse).reverse)

    def addChild(child: Text): Normal = copy(children = (child :: children.reverse).reverse)

    @targetName("addChild(Element)")
    def /(child: Element.Normal): Normal = addChild(child)

    @targetName("addChild(Text)")
    def /(child: Text): Normal = addChild(child)

    @targetName("addAttribute(Normal)")
    def :=(attribute: Attribute): Normal = addAttribute(attribute)
  }
}
