package utils

object DigitUtils {

  def findDigit(str: String): Int = {
    str match {
      case str if str.startsWith("one") || str.startsWith("1") => 1
      case str if str.startsWith("two") || str.startsWith("2") => 2
      case str if str.startsWith("three") || str.startsWith("3") => 3
      case str if str.startsWith("four") || str.startsWith("4") => 4
      case str if str.startsWith("five") || str.startsWith("5") => 5
      case str if str.startsWith("six") || str.startsWith("6") => 6
      case str if str.startsWith("seven") || str.startsWith("7") => 7
      case str if str.startsWith("eight") || str.startsWith("8") => 8
      case str if str.startsWith("nine") || str.startsWith("9") => 9
    }
  }
}
