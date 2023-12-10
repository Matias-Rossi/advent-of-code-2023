
import model.charData.SymbolData
import model.{PartNumber, RowData}

import scala.io.Source

object Main {

  def main(args: Array[String]): Unit = {
    // Part Two
    val bufferedSource = Source.fromFile("src/main/resources/input.txt")
    val symbolAndDigitIndexesPerLine: List[RowData] = RowData.emptyRow :: GetRowDataForLines(bufferedSource.getLines()) ::: List(RowData.emptyRow)

    val answerB: Int = symbolAndDigitIndexesPerLine
      .sliding(3).collect { case List(a, b, c) => (a, b, c) }.toList
      .foldLeft(0) {
        case (acc, (rowBefore: RowData, rowToBeProcessed: RowData, rowAfter: RowData)) =>
        acc + GetGearRatios(rowBefore, rowToBeProcessed, rowAfter) //81721933
      }

    println("The sum of all of the gear ratios in the engine schematic is " + answerB)
  }

}

object GetGearRatios {
  def apply(rowBefore: RowData, rowBeingProcessed: RowData, rowAfter: RowData): Int = {
    rowBeingProcessed.gears.map(_.getGearRatio(rowBefore, rowBeingProcessed, rowAfter)).sum
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