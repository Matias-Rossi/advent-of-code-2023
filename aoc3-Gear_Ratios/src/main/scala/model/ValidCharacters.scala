package model

object ValidCharacters extends Enumeration {
  type ValidCharacters = Value
  val Symbol, Digit, Void = Value

  def typeOf(char: Char): ValidCharacters = {
    char match {
      case c if c.isDigit => Digit
      case c if !c.isDigit && c != '.' => Symbol
      case _ => Void
    }
  }
}
