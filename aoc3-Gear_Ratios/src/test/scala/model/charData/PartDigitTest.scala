package model.charData

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class PartDigitTest extends AnyFreeSpec {
  "0 gets converted correctly" in {
    PartDigit.fromCharWithIndex('0', 0) shouldBe PartDigit(0, 0)
  }
  "9 gets converted correctly" in {
    PartDigit.fromCharWithIndex('9', 154) shouldBe PartDigit(9, 154)

  }
}
