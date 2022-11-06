class TextUtilSuite extends munit.FunSuite {
  test("escape") {
    val result = TextUtil.escape("<>&\"'    ")
    assert(result == "&lt;&gt;&amp;&quote;&#x27;")
  }
}
