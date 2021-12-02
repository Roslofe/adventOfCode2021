package day2
import scala.io.Source

object diveWithAim extends App {

  val file = Source.fromFile("day2/directions.txt")

  /** Functions identically to the dive-object, with
   * the only exception being the addition of the
   * aim-variable, and what changes each command does */
  try {
    var horizontal = 0
    var depth = 0
    var aim = 0
    val directions = file.getLines().toVector.map(_.split(" "))
    for (command <- directions) {
      val goTo = command(0)
      val howMuch = command(1).toInt
      /** Goes through each possible command, and changes the value for said direction accordingly. */
      if (goTo == "forward") {
        horizontal += howMuch
        depth += howMuch * aim
      } else if (goTo == "down")
        aim += howMuch
      else if (goTo == "up")
        aim -= howMuch
    }
    println(s"Final coordinates:\nDepth = $depth\nHorizontal = $horizontal\nMultiplied: ${depth * horizontal}")
  } finally {
    file.close()
  }

}