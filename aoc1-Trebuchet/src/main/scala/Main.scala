import utils.StringUtils

import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    val bufferedSourceA = Source.fromFile("src/main/resources/input.txt")
    val bufferedSourceB = Source.fromFile("src/main/resources/input.txt")


    // --- Part A ---
    val answerA = bufferedSourceA.getLines()
      .map(line => line.filter(_.isDigit))
      .map(StringUtils.getFirstAndLastChar)
      .map(tuple => tuple._1.toString + tuple._2.toString)
      .map(_.toInt).sum
    println("The sum of all calibration values is " + answerA) //55386

    // --- Part B ---
    val answerB = bufferedSourceB.getLines()
      .map(StringUtils.getDigits)
      .map(StringUtils.getFirstAndLastChar)
      .map(tuple => tuple._1.toString + tuple._2.toString)
      .map(_.toInt).sum
    println("The sum of all calibration values is " + answerB) //54824
  }
}