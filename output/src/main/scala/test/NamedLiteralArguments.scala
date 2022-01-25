package test

object NamedLiteralArguments {
  def complete(isSuccess: Boolean): Unit = ()
  def finish(n: Int, isError: Boolean): Unit = ()
  complete(isSuccess = true)
  complete(isSuccess = true)
  complete(isSuccess = false)
  finish(2, isError = true)
}
