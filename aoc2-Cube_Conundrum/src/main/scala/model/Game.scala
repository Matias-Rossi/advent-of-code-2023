package model

import model.Cube.Cube

case class Game(id: Int, cubeQuantities: Map[Cube, Int]) {
  def isPossibleGiven(availableCubes: Map[Cube, Int]): Boolean = {
    availableCubes.toSeq.forall {
      case (color, availableQuantity) => availableQuantity - cubeQuantities(color) >= 0
    }
  }

}
