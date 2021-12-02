package day2
import scala.io.Source

object Dive extends App {

  val file = Source.fromFile("day2/directions.txt")

  try {
    var horizontal = 0
    var depth = 0
    val directions = file.getLines().toVector.map(_.split(" "))
    for (command <- directions) {
      val goTo = command(0)
      val howMuch = command(1).toInt
      /** Goes through each possible command, and changes the value for said direction accordingly. */
      if (goTo == "forward")
        horizontal += howMuch
      else if (goTo == "down")
        depth += howMuch
      else if (goTo == "up")
        depth -= howMuch
    }
    println(s"Final coordinates:\nDepth = $depth\nHorizontal = $horizontal\nMultiplied: ${depth * horizontal}")
  } finally {
    file.close()
  }

}