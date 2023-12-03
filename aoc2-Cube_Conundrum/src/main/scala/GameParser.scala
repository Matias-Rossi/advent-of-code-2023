import model.Cube.Cube
import model.{Cube, Game}


object GameParser {
  private type CubeMap = Map[Cube, Int]

  def parseGame(str: String): Game = {
    val Array(id, sets) = str.split(':')
    val revealedSets = sets.split(';')
    val cubes = getMaxByCube(revealedSets.map(parseSet))
    Game(id.substring(5).toInt, cubes)
  }

  private def parseSet(str: String): CubeMap = {
    str.split(',').map(parseCube).toMap
  }

  private def parseCube(str: String): (Cube, Int) = {
    val split = str.trim.split(' ')
    (Cube.fromString(split(1)), split(0).toInt)
  }

  private def getMaxByCube(cubes: Array[CubeMap]): CubeMap = {
    val noCubesMap = Map(Cube.Red -> 0, Cube.Green -> 0, Cube.Blue -> 0)
    cubes.foldRight(noCubesMap)(compareCubesMap)
  }

  private def compareCubesMap(a: CubeMap, b: CubeMap): CubeMap = {
    (a.toSeq ++ b.toSeq).groupMapReduce(_._1)(_._2)(math.max)
  }

}
