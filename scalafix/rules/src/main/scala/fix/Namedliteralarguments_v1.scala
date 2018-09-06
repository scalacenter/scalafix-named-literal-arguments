package fix

import scalafix.v1._
import scala.meta._

class Namedliteralarguments_v1
    extends SemanticRule("Namedliteralarguments_v1") {

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree
      .collect {
        case Term.Apply(_, args) =>
          args.collect {
            case t @ Lit.Boolean(_) =>
              Patch.addLeft(t, "isSuccess = ")
          }
      }
      .flatten
      .asPatch
  }

}
