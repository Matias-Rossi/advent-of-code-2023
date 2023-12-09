package model

import model.charData.SymbolData

import scala.annotation.tailrec

case class PartNumber(value: Int, fromIndex: Int, toIndex: Int)

object PartNumber {
  def fromRowData(rowData: RowData): List[PartNumber] = {
    groupBySequentialIndices(rowData.partDigits).map(digitsToNumber)
  }

  private def groupBySequentialIndices(elements: List[SymbolData]): List[List[SymbolData]] = {
    groupBySequentialIndexRecursion(elements)
  }

  @tailrec
  private def groupBySequentialIndexRecursion(
                                               partDigits: List[SymbolData],
                                               currentGroup: List[SymbolData] = Nil,
                                               result: List[List[SymbolData]] = Nil
                                             ): List[List[SymbolData]] = {
    partDigits match {
      case Nil => result :+ currentGroup.reverse
      case head :: tail =>
        if (currentGroup.isEmpty || head.index == currentGroup.head.index + 1) {
          groupBySequentialIndexRecursion(tail, head :: currentGroup, result)
        } else {
          groupBySequentialIndexRecursion(tail, List(head), result :+ currentGroup.reverse)
        }
    }
  }

  private def digitsToNumber(digits: List[SymbolData]): PartNumber = {
    val minIndex = digits.minBy(_.index()).index()
    val maxIndex = digits.maxBy(_.index()).index()
    val value = digits.foldLeft("")((string, digit) => string.+(digit.value().toString))
    PartNumber(value.toInt, minIndex, maxIndex)
  }
}

