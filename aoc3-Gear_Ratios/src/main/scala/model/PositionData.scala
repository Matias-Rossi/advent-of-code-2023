package model

import model.ValidCharacters.ValidCharacters

case class PositionData(typeContained: ValidCharacters, index: Int, partValue: Int = 0)

object PositionData {
  def fromCharAndIndex(char: Char, index: Int): PositionData = {
    char match {
      case char if char.isDigit => PositionData(ValidCharacters.typeOf(char), index, char.toInt - '0')
      case _ => PositionData(ValidCharacters.typeOf(char), index)
    }

  }
}
