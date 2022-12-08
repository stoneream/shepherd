import html.Tags

@main def main(): Unit = {
  val document = Tags.html.addChild(Tags.head).addChild(Tags.body.addChild(Tags.div).addChild(Tags.div))
}
