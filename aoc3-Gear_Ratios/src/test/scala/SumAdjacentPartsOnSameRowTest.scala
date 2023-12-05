import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class SumAdjacentPartsOnSameRowTest extends AnyFreeSpec {
  "given a line" - {
    val line = "../..*8..4/4/"
    "it returns the sum of digits placed adjacently to symbols" in {
      SumAdjacentPartsOnSameRow(GetSymbolAndDigitIndexesFromLine(line)) shouldBe 16
    }
  }
}
