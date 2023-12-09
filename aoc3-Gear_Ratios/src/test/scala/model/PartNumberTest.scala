package model

import model.charData.{EngineSymbol, PartDigit}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class PartNumberTest extends AnyFreeSpec {
  "fromRowData" - {
    "a row gets its numbers parsed correctly" in {
        val rowData = RowData(List(PartDigit(2, 0), PartDigit(3, 1), PartDigit(5, 3)), List(EngineSymbol(2)))
        PartNumber.fromRowData(rowData) shouldBe List(PartNumber(23, 0, 1), PartNumber(5, 3, 3))
      }
    "another row" in {
      val rowData = RowData(List(PartDigit(1, 3), PartDigit(2, 4), PartDigit(1, 9)), Seq(0, 1, 2, 5, 6, 7, 8).map(EngineSymbol).toList)
      PartNumber.fromRowData(rowData) shouldBe List(PartNumber(12, 3, 4), PartNumber(1, 9, 9))
    }
    "row with composite number at the end" in {
      val rowData = RowData(List(PartDigit(1, 3), PartDigit(7, 8), PartDigit(9, 9)), Seq(0, 1, 2, 5, 6, 7).map(EngineSymbol).toList)
      PartNumber.fromRowData(rowData) shouldBe List(PartNumber(1, 3, 3), PartNumber(79, 8, 9))
    }
    "row with composite numbers at start, middle and end" in {
      val rowData = RowData(
        List(PartDigit(1, 0), PartDigit(0, 1), PartDigit(1, 3), PartDigit(2, 4), PartDigit(9, 6), PartDigit(9, 7)),
        List(EngineSymbol(2), EngineSymbol(5))
      )
      PartNumber.fromRowData(rowData) shouldBe List(PartNumber(10, 0, 1), PartNumber(12, 3, 4), PartNumber(99, 6, 7))
    }

    "row with single digit numbers at start, middle and end" in {
      val rowData = RowData(
        List(PartDigit(1, 0), PartDigit(5, 2), PartDigit(2, 4)),
        List(EngineSymbol(1), EngineSymbol(3))
      )
      PartNumber.fromRowData(rowData) shouldBe List(PartNumber(1, 0, 0), PartNumber(5, 2, 2), PartNumber(2, 4, 4))
    }

  }

}
