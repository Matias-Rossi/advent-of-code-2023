
import model.charData.SymbolData
import model.{PartNumber, RowData}

import scala.io.Source

object Main {

  def main(args: Array[String]): Unit = {
    // Part One
    val bufferedSourceA = Source.fromFile("src/main/resources/input.txt")
    val symbolAndDigitIndexesPerLine: List[RowData] = RowData.emptyRow :: GetRowDataForLines(bufferedSourceA.getLines()) ::: List(RowData.emptyRow)

    val answerA: Int = symbolAndDigitIndexesPerLine
      .sliding(3).collect { case List(a, b, c) => (a, b, c) }.toList
      .foldLeft(0) {
        case (acc, (rowBefore: RowData, rowToBeProcessed: RowData, rowAfter: RowData)) =>
        acc + SumPartsWithSymbolsInRange(rowBefore, rowToBeProcessed, rowAfter)
      }

    println("The sum of all the part numbers in the engine schematic is " + answerA)

  }

}

object SumPartsWithSymbolsInRange {
  def apply(rowBefore: RowData, rowBeingProcessed: RowData, rowAfter: RowData): Int = {
    rowBeingProcessed.partNumbers
      .filter(number => PartNumberHasEngineSymbolInRange(number, rowBefore, rowBeingProcessed, rowAfter))
      .map(_.value).sum
  }

  private def PartNumberHasEngineSymbolInRange(number: PartNumber, rowBefore: RowData, rowBeingProcessed: RowData, rowAfter: RowData): Boolean = {
    rowAfter.symbols.exists(symbol => SymbolIsInPartRange(number, symbol)) ||
      rowBeingProcessed.symbols.exists(symbol => SymbolIsInPartRange(number, symbol)) ||
      rowBefore.symbols.exists(symbol => SymbolIsInPartRange(number, symbol))
  }

  private def SymbolIsInPartRange(number: PartNumber, symbol: SymbolData): Boolean = {
    val value = number.fromIndex - 1<= symbol.index && symbol.index <= number.toIndex + 1
    value
  }
}

object GetRowDataForLines extends (Iterator[String] => List[RowData]) {
  override def apply(lines: Iterator[String]): List[RowData] = {
    lines.map(line => RowData.fromCharList(line.toList)).toList
  }
}

object SumAdjacentPartsAcrossRows extends ((RowData, RowData) => Int) {
  override def apply(firstRow: RowData, secondRow: RowData): Int = {
    val firstRowDigits = sumValuesAcross2Rows(firstRow, secondRow)

    val secondRowDigits = sumValuesAcross2Rows(secondRow, firstRow)

    firstRowDigits + secondRowDigits
  }

  private def sumValuesAcross2Rows(row1: RowData, row2: RowData): Int = {
    row1.partNumbers
      .filter(number =>
        row2.symbols.exists(symbolData => (number.fromIndex-1) <= symbolData.index && symbolData.index <= (number.toIndex+1))
      ).map(_.value).sum
  }
}