package model.charData

import model.RowData

case class PossibleGear(
                 override val index: Int
               ) extends SymbolData {
  override def getGearRatio(rowBefore: RowData, rowBeingProcessed: RowData, rowAfter: RowData): Int = {
    val partNumbersInRange = Seq(rowBefore, rowBeingProcessed, rowAfter).flatMap(_.partNumbers)
      .filter(pn => (pn.fromIndex - 1) <= this.index && this.index <= (pn.toIndex + 1)).toList

    partNumbersInRange match {
      case gearPartNumbers if gearPartNumbers.length == 2 => partNumbersInRange.map(pn => pn.value).product
      case _ => 0
    }

  }
}

