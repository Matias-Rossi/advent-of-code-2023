import Main.LineCharacterMap
import model.{PositionData, ValidCharacters}
import model.ValidCharacters.ValidCharacters

import scala.collection.IterableOnce.iterableOnceExtensionMethods
import scala.collection.MapView
import scala.io.Source

object Main {
  type LineCharacterMap = MapView[ValidCharacters, List[PositionData]]

  def main(args: Array[String]): Unit = {
    // Part One
    val bufferedSourceA = Source.fromFile("src/main/resources/input.txt")
    val symbolAndDigitIndexesPerLine: List[LineCharacterMap] = bufferedSourceA.getLines()
      .map(GetSymbolAndDigitIndexesFromLine).toList

    val horizontallyAdjacentParts = symbolAndDigitIndexesPerLine.map(SumAdjacentPartsOnSameRow).sum

    val diagonalAdjacentNumbers: Int = symbolAndDigitIndexesPerLine
      .sliding(2).collect { case List(a, b) => (a, b) }.toList
      .foldLeft(0) {
        case (acc, (firstRow: LineCharacterMap, secondRow: LineCharacterMap)) =>
        acc + SumAdjacentPartsAcrossRows(firstRow, secondRow)
      }

    val answerA: Int = diagonalAdjacentNumbers + horizontallyAdjacentParts
    println("The sum of all the part numbers in the engine schematic is " + answerA)

  }

}

object GetSymbolAndDigitIndexesFromLine extends (String => LineCharacterMap) {
  override def apply(str: String): LineCharacterMap = {
    str.zipWithIndex.map(charIndex => PositionData.fromCharAndIndex(charIndex._1, charIndex._2))
      .groupBy(_.typeContained).-(ValidCharacters.Void).view.mapValues(_.toList)
  }
}

object SumAdjacentPartsAcrossRows extends ((LineCharacterMap, LineCharacterMap) => Int) {
  override def apply(firstRow: LineCharacterMap, secondRow: LineCharacterMap): Int = {
    val firstRowDigits = sumValuesAcross2Rows(firstRow, secondRow)

    val secondRowDigits = sumValuesAcross2Rows(secondRow, firstRow)

    firstRowDigits + secondRowDigits
  }

  private def sumValuesAcross2Rows(row1: LineCharacterMap, row2: LineCharacterMap): Int = {
    row1.getOrElse(ValidCharacters.Digit, List())
      .filter(digitData =>
        row2.getOrElse(ValidCharacters.Symbol, List()).exists(symbolData => (digitData.index - symbolData.index).abs <= 1)
      ).map(_.partValue).sum
  }
}

object SumAdjacentPartsOnSameRow extends (LineCharacterMap => Int) {
  override def apply(indexMap: LineCharacterMap): Int = {
    indexMap.getOrElse(ValidCharacters.Digit, List()).filter(digitData =>
      indexMap.getOrElse(ValidCharacters.Symbol, List()).exists(symbolData => (symbolData.index - digitData.index).abs == 1)
    ).map(_.partValue).sum
  }
}