/*
rule = NoLiteralArguments
 */
package test

class NoLiteralArguments {
  def complete(isSuccess: Boolean): Unit = ()
  complete(true) // assert: NoLiteralArguments
  complete(true) // scalafix:ok; should not assert
  complete(false) /* assert: NoLiteralArguments
           ^^^^^
  Use named arguments for literals such as 'parameterName = false'
 */

}
