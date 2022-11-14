// https://momdo.github.io/html/syntax.html#elements-2
sealed trait HTMLElement {
  val name: String
  val attributes: List[HTMLAttribute]
}

object HTMLElement {
  trait VoidElement extends HTMLElement
  trait EscapableRawTextElement extends HTMLElement {
    val text: String
  }
  trait NormalElement extends HTMLElement {
    val children: List[HTMLElement | String]
  }
}

// todo validateion
