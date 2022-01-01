package day5
import scala.io._
import scala.math._
import scala.collection.mutable.Map

object ventOverlap extends App {

  val file = Source.fromFile("day5/lines.txt")

  /** The given file provides a pair of coordinates.
   * The purpose of the program is to "draw" vertical
   * and horizontal lines connecting the coodrinates.
   * Then, it returns the amount of points where at
   * least two lines overlap. */
  try {
    val lineParts = file.getLines()
    var currentOverlaps = Map[(Int, Int), Int]()
    for (line <- lineParts) {
      // The given row is split so that a variable exists for each part of the coordinates.
      val x1 = line.split(" -> ").head.split(",").head.toInt
      val y1 = line.split(" -> ").head.split(",").last.toInt
      val x2 = line.split(" -> ").last.split(",").head.toInt
      val y2 = line.split(" -> ").last.split(",").last.toInt
      // Vertical lines
      if (x1 == x2) {
        val beginning = min(y1, y2)
        val difference = max(y1, y2) - beginning
        /** Each point along the given line is analysed. If
         * there's already a line in the coordinate, the number
         * stored in currentOverlaps is increased by 1. Otherwise
         * a key is created for the coordinates, and given the
         * value 1.*/
        for ( i <- 0 to difference) {
          val newY = beginning +  i
          val lineInfo = currentOverlaps.getOrElse((x1, newY), 0)
          currentOverlaps((x1, newY)) = lineInfo + 1
        }
        // Functions the same way as the above code, just for horizontal lines.
      } else if (y1 == y2) {
        val beginning = min(x1, x2)
        val difference = max(x1, x2) - beginning
        for ( i <- 0 to difference) {
          val newX = beginning +  i
          val lineInfo = currentOverlaps.getOrElse((newX, y1), 0)
          currentOverlaps((newX, y1)) = lineInfo + 1
        }
      }
    }
    // As all values above 1 indicate overlap, their count gives the result to the question.
    val result = currentOverlaps.values.count(_ > 1)
    println(s"There are $result overlapping lines")
  } finally {
    file.close()
  }
}