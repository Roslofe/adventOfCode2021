package day5

import scala.collection.mutable.Map
import scala.io._
import scala.math._

object diagonalOverlap extends App {

  val file = Source.fromFile("day5/lines.txt")

  // The purpose and function is identical to ventOverlap, just with the inclusion of diagonal lines overlapping at a 45-degree angle.
  try {
    val lineParts = file.getLines()
    var currentOverlaps = Map[(Int, Int), Int]()
    for (line <- lineParts) {
      val x1 = line.split(" -> ").head.split(",").head.toInt
      val y1 = line.split(" -> ").head.split(",").last.toInt
      val x2 = line.split(" -> ").last.split(",").head.toInt
      val y2 = line.split(" -> ").last.split(",").last.toInt
      if (x1 == x2) {
        val beginning = min(y1, y2)
        val difference = max(y1, y2) - beginning
        for ( i <- 0 to difference) {
          val newY = beginning +  i
          val lineInfo = currentOverlaps.getOrElse((x1, newY), 0)
          currentOverlaps((x1, newY)) = lineInfo + 1
        }
      } else if (y1 == y2) {
        val beginning = min(x1, x2)
        val difference = max(x1, x2) - beginning
        for ( i <- 0 to difference) {
          val newX = beginning +  i
          val lineInfo = currentOverlaps.getOrElse((newX, y1), 0)
          currentOverlaps((newX, y1)) = lineInfo + 1
        }
      } else if (x1 - x2 == y1 - y2) {
        val beginningX = min(x1, x2)
        val beginningY = min(y1, y2)
        val difference = max(y1, y2) - beginningY
        for (i <- 0 to difference) {
          val lineInfo = currentOverlaps.getOrElse((beginningX + i, beginningY + i), 0)
          currentOverlaps((beginningX + i, beginningY + i)) = lineInfo + 1
        }
      } else if (abs(x1 - x2) == abs(y1 - y2)) {
        val beginningX = min(x1, x2)
        val beginningY = max(y1, y2)
        val difference = abs(x1 - x2)
        for ( i <- 0 to difference) {
          val lineInfo = currentOverlaps.getOrElse((beginningX + i, beginningY - i), 0)
          currentOverlaps((beginningX + i, beginningY - i)) = lineInfo + 1
        }
      }
    }
    val result = currentOverlaps.values.count(_ > 1)
    println(s"There are $result overlapping lines")
  } finally {
    file.close()
  }
}
