/*
rule = NoLiteralArguments
NoLiteralArguments.disabledLiterals = [
  Int
  String
  Boolean
]
 */
package test

class NoLiteralArgumentsConfig {
  def complete(message: String): Unit = ()
  def complete(count: Int): Unit = ()
  def complete(isSuccess: Boolean): Unit = ()
  def complete(code: Char): Unit = ()
  complete("done") // assert: NoLiteralArguments
  complete(42) // assert: NoLiteralArguments
  complete(true) // assert: NoLiteralArguments
  complete(true) // scalafix:ok; rule suppression
  complete('o')
}
