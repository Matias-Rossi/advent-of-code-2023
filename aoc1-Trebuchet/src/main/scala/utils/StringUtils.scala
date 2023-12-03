package utils

import scala.util.Try

object StringUtils {
  def getFirstAndLastChar(str: String): (Char, Char) = {
    (str.charAt(0), str.charAt(str.length-1))
  }

  def getDigits(str: String): String = {
    getSubstringsStartingFromEachIndex(str)
      .flatMap(substr => Try(DigitUtils.findDigit(substr).toString).getOrElse("")).mkString
  }

  def getSubstringsStartingFromEachIndex(str: String): List[String] = {
    val substrings = for (i <- 0 until str.length) yield str.substring(i, str.length)
    substrings.toList
  }
}
