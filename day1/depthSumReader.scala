package day1
import scala.io.Source

object depthSumReader extends App {

  val file = Source.fromFile("day1/values.txt")

  /** The app initially goes through the original values, and
   * turns them into a new vector containing the sums of each
   * group of three measurements. It stops once there aren't
   * enough measurements to form a group of three.
   *
   * After that, it functions similarly to the depthReader-app,
   * with the exception of analyzing the sum values instead of
   * individual ones.*/
  try {
    val valuesInLines = file.getLines().toVector.map(_.toInt)
    var tripletSums = Vector[Int]()
    var currentLine = 0
    while (currentLine < valuesInLines.size - 2) {
      val firstValue = valuesInLines(currentLine)
      val secondValue = valuesInLines(currentLine + 1)
      val thirdValue = valuesInLines(currentLine + 2)
      val sumValue = firstValue + secondValue + thirdValue
      tripletSums = tripletSums :+ sumValue
      currentLine += 1
    }
    currentLine = 1
    var largerThanLast = 0
    while (currentLine < tripletSums.size) {
      val currentSum = tripletSums(currentLine)
      val previousSum = tripletSums(currentLine - 1)
      if (currentSum > previousSum)
        largerThanLast += 1
      currentLine += 1
    }
    println(s"There were $largerThanLast measurement increases")

  } finally {
    file.close()
  }

}