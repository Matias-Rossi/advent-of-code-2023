import model.Card

import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    val bufferedSource = Source.fromFile("src/main/resources/input.txt")
    val lines = bufferedSource.getLines()
    val answer = lines.map(Card.parseFromString(_).getPoints).sum
    println(String.format("There are %.0f points in total", answer)) //25651
  }
}