import model.{PositionData, ValidCharacters}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import scala.collection.MapView

class GetSymbolAndDigitIndexesFromLineTest extends AnyFreeSpec {
  "Given a line" - {
    val line = "$./.8+.2"
    "it gets its symbols and digit indexes" in {
      GetSymbolAndDigitIndexesFromLine(line).toMap shouldBe
        Map(
          ValidCharacters.Symbol -> List(
            PositionData(ValidCharacters.Symbol, 0),
            PositionData(ValidCharacters.Symbol, 2),
            PositionData(ValidCharacters.Symbol, 5)
          ),
          ValidCharacters.Digit -> List(
            PositionData(ValidCharacters.Digit, 4, 8),
            PositionData(ValidCharacters.Digit, 7, 2)
          )
        )
    }
  }
}
