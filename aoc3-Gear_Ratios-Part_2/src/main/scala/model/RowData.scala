package model

import model.charData.{EngineSymbol, PossibleGear, PartDigit, SymbolData}

case class RowData(partDigits: List[SymbolData], symbols: List[SymbolData], gears: List[SymbolData] = List()) {
  def partNumbers: List[PartNumber] = PartNumber.fromRowData(this)
}

object RowData {
  def emptyRow: RowData = RowData(List(), List())

  def fromCharList(chars: List[Char]): RowData = {
    val symbolMap: Map[String, List[SymbolData]] = chars.zipWithIndex.foldLeft(emptySymbolMap())(
      (map, tuple) => tuple._1 match {
        case digit if digit.isDigit => map("PartDigit") match {
          case xs => map.updated("PartDigit", xs :+ PartDigit.fromCharWithIndex(digit, tuple._2))
        }
        case void if void == '.' => map
        case gear if gear == '*' => map("Gear") match {
          case xs => map.updated ("Gear", xs :+ PossibleGear(tuple._2))
        }
        case a => map("EngineSymbol") match {
          case xs => map.updated("EngineSymbol", xs :+ EngineSymbol(tuple._2))
        }
      }
    )
    RowData(symbolMap("PartDigit"), symbolMap("EngineSymbol"), symbolMap("Gear"))
  }

  private def emptySymbolMap(): Map[String , List[SymbolData]] = {
    Map(
      "PartDigit" -> List(),
      "EngineSymbol" -> List(),
      "Gear" -> List()
    )
  }

}
