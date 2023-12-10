package model.charData

case class PartDigit(
                       override val value: Int,
                       override val index: Int
                     ) extends SymbolData

object PartDigit {
  def fromCharWithIndex(char: Char, index: Int): PartDigit = {
    PartDigit(char.toInt-'0', index)
  }
}
