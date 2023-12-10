package model.charData

import model.RowData

trait SymbolData {
  def value(): Int = 0
  def index(): Int

  def getGearRatio(a: RowData, b: RowData, c: RowData): Int = 0 //Quick solution, I'm behind schedule!
}
