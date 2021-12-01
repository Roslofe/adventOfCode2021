package day1
import scala.io.Source

object depthReader extends App {

  val file = Source.fromFile("day1/values.txt")

  try {
    /** Initially this creates a vector that includes all
     * of the depth values in the given document. */
    val valuesInLines = file.getLines().toVector.map(_.toInt)
    var currentLine = 1
    var largerThanLast = 0
    /** The while-loop goes through each value; excluding the
     * first one, as there is nothing to compare to, in the
     * valuesInLines-vector, and compares it with the previous
     * value. If the current value is larger, the program increases
     * the number of values that are larger than the previous
     * one by 1. This is stored in the variable largerThanLast.
     * After analyzing all rows. The program prints the number
     * of increases, and closes itself. */
    while (currentLine < valuesInLines.size) {
      val currentValue = valuesInLines(currentLine)
      val previousValue = valuesInLines(currentLine - 1)
      if (currentValue > previousValue)
        largerThanLast += 1
      currentLine += 1
    }
    println(s"There were $largerThanLast measurement increases")

  } finally {
    file.close()
  }

}
