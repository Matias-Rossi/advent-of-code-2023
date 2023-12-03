import model.Cube

import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    // Part One
    val bufferedSourceA = Source.fromFile("src/main/resources/input.txt")
    val availableCubes = Map(Cube.Red->  12, Cube.Green -> 13, Cube.Blue -> 14)
    val answerA = bufferedSourceA.getLines()
      .map(GameParser.parseGame)
      .filter(_.isPossibleGiven(availableCubes))
      .map(_.id)
      .sum
    println("The sum of the possible games ID is " + answerA) // 2541

    // Part Two
    val bufferedSourceB = Source.fromFile("src/main/resources/input.txt")
    val answerB = bufferedSourceB.getLines()
      .map(GameParser.parseGame(_).getPower)
      .sum
    println("The sum of the power of the minimum set of cubes that" +
      " had to be present for each game is " + answerB) // 66016
  }
}