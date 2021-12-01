package day1
import scala.io.Source

object depthReader extends App {

  val file = Source.fromFile("day1/values.txt")

  try {
    val valuesInLines = file.getLines().toVector.map(_.toInt)
    var currentLine = 1
    var largerThanLast = 0
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
