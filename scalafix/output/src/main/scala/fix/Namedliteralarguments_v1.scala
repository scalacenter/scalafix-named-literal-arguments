package fix

object Namedliteralarguments_v1_Test {
  def complete(isSuccess: Boolean): Unit = ()
  def finish(isError: Boolean): Unit = ()
  complete(isSuccess = true)
  complete(isSuccess = true)
  complete(isSuccess = false)
  finish(isError = true)
}
