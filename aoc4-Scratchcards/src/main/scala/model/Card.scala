package model

case class Card(winningNumbers :List[Int], containedNumbers: List[Int]) {
  def getPoints: Double = {
    winningNumbers.intersect(containedNumbers).length match {
      case exponent if exponent > 0 => Math.pow(2, winningNumbers.intersect(containedNumbers).length - 1)
      case _ => 0
    }

  }
}
// 1 * 2 * 2 * 2
object Card {
  def parseFromString(str: String): Card = {
    // All of this could just be one function, but I might need the modularity and extensibility for part 2
    val (winningNumbers, cardNumbers) = str.substring(str.indexOf(':'.toInt) + 1).split('|').toList match {
      case List(w, c) => (w, c)
      case _ => throw new RuntimeException("Invalid input")
    }
    Card(
      ParsingUtils.spaceSeparatedNumbersToIntList(winningNumbers),
      ParsingUtils.spaceSeparatedNumbersToIntList(cardNumbers)
    )
  }

}


case object ParsingUtils {
  def spaceSeparatedNumbersToIntList(str: String): List[Int] = {
    str.split(' ').map(_.trim).filterNot(_=="").map(_.toInt).toList
  }
}
