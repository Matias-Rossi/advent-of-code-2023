package model

object Cube extends Enumeration {

  type Cube = Value
  val Blue, Green, Red = Value

  def fromString(str: String): Cube = {
    str.toLowerCase match {
      case "blue" => Blue
      case "green" => Green
      case "red" => Red
    }
  }
}
