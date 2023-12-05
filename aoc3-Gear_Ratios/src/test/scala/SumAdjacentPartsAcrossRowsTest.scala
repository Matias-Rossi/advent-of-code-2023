import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class SumAdjacentPartsAcrossRowsTest extends AnyFreeSpec {
  "Given two lines" - {
    //Yes, I'm aware this isn't unit testing
    val line1 = GetSymbolAndDigitIndexesFromLine("..../...841...*+.2")
    val line2 = GetSymbolAndDigitIndexesFromLine("....4...421..1..$.")
    "it sums numbers adjacent to parts vertically or diagonally" in {
      SumAdjacentPartsAcrossRows(line1, line2) shouldBe 7
    }
  }
}
