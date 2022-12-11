import html.{Attributes, Tags}

@main def main(): Unit = {

  val document = Tags.html
    / Tags.head
    / (
      Tags.body
        / (Tags.div := Attributes.id("child-1"))
        / (Tags.div := Attributes.id("child-2"))
    )

  println(document.children)

}
