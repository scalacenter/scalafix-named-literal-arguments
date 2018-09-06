/*
rule = NamedLiteralArguments
*/
package test

object NamedLiteralArguments {
  def complete(isSuccess: Boolean): Unit = ()
  def finish(n: Int, isError: Boolean): Unit = ()
  complete(true)
  complete(isSuccess = true)
  complete(false)
  finish(2, true)
}
