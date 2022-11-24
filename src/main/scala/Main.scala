import Tags.*

@main def main(): Unit = {

  val tag = div ~ id"parent" / (
    div ~ id"child1" / (
      div ~ id"child1-1",
      div ~ id"child1-2"
    ),
    div ~ id"child2" / (
      p / "こんちわ"
    )
  )

  val html = HTMLGenerator.generate(tag)

  println(html)
  /*
  <div id="parent">
    <div id="child1">
      <div id="child1-1">
      </div>
      <div id="child1-2">
      </div>
    </div>
    <div id="child2">
      <p >
        こんちわ
      </p>
    </div>
  </div>
   */
}
