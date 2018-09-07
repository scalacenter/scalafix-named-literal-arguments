package fix

import metaconfig.Configured
import scala.meta._
import scalafix.v1._

case class LiteralArgument(literal: Lit) extends Diagnostic {
  override def position: Position = literal.pos
  override def message: String =
    s"Use named arguments for literals such as 'parameterName = $literal'"
}

case class NoLiteralArgumentsConfig(
    disabledLiterals: List[String] = List("Boolean")
) {
  def isDisabled(lit: Lit): Boolean = {
    val kind = lit.productPrefix.stripPrefix("Lit.")
    disabledLiterals.contains(kind)
  }
}

object NoLiteralArgumentsConfig {
  val default = NoLiteralArgumentsConfig()
  implicit val surface =
    metaconfig.generic.deriveSurface[NoLiteralArgumentsConfig]
  implicit val decoder =
    metaconfig.generic.deriveDecoder(default)
}

class NoLiteralArguments(config: NoLiteralArgumentsConfig)
    extends SyntacticRule("NoLiteralArguments") {
  def this() = this(NoLiteralArgumentsConfig.default)
  override def withConfiguration(config: Configuration): Configured[Rule] = {
    config.conf
      .getOrElse("NoLiteralArguments")(this.config)
      .map(newConfig => new NoLiteralArguments(newConfig))
  }
  override def fix(implicit doc: SyntacticDocument): Patch = {
    doc.tree
      .collect {
        case Term.Apply(_, args) =>
          args.collect {
            case t: Lit if config.isDisabled(t) =>
              Patch.lint(LiteralArgument(t))
          }
      }
      .flatten
      .asPatch
  }
}
