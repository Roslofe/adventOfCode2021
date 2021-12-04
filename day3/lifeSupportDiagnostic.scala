package day3
import scala.io._

object lifeSupportDiagnostic extends App {

  val file = Source.fromFile("day3/binary.txt")
  val lines = file.getLines().toVector.map(_.split("")).map(_.map(_.toInt))

  try {
    /** Finds the most common value for the n'th index, and filters the
     * remaining lines according to it, until one line remains. In case
     * there is equal numbers of 1 and 0, 1 is chosen. */
    def findOxygen() = {
      var linesLeft = lines
      var optionsLeft = lines.size
      var currentIndex = 0
      while (optionsLeft > 1) {
        var zeroes = 0
        var ones = 0
        for (line <- linesLeft) {
          val lookingAt = line(currentIndex)
          if (lookingAt == 0)
            zeroes += 1
          else
            ones += 1
        }
        if (zeroes > ones)
          linesLeft = linesLeft.filter(_(currentIndex) == 0)
        else
          linesLeft = linesLeft.filter(_(currentIndex) == 1)
        currentIndex += 1
        optionsLeft = linesLeft.size
      }
      println("Oxygen generator rating: ")
      linesLeft.foreach(_.foreach(print))
    }
    /** Finds the least common value, and filters according to
     * it, until one line remains. In case there is equal amounts,
     * 0 is favoured. */
    def findCO2() = {
      var linesLeft = lines
      var optionsLeft = lines.size
      var currentIndex = 0
      while (optionsLeft > 1) {
        var zeroes = 0
        var ones = 0
        for (line <- linesLeft) {
          val lookingAt = line(currentIndex)
          if (lookingAt == 0)
            zeroes += 1
          else
            ones += 1
        }
        if (zeroes > ones)
          linesLeft = linesLeft.filter(_(currentIndex) == 1)
        else
          linesLeft = linesLeft.filter(_(currentIndex) == 0)
        currentIndex += 1
        optionsLeft = linesLeft.size
      }
      println("\nCO2 scrubber rating: ")
      linesLeft.foreach(_.foreach(print))
    }
    findOxygen()
    findCO2()
  } finally {
    file.close()
  }

}
