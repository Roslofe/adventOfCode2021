package day3
import scala.io.Source

object binaryDiagnostic extends App {

  val file = Source.fromFile("day3/binary.txt")


  try {
    val allDigits = file.getLines().toVector.map(_.split("")).map(_.map(_.toInt))
    var gammaRate = "2"
    var epsilonRate = "2"
    var currentIndex = 0
    while (currentIndex < allDigits(0).length) {
      var zeroes = 0
      var ones = 0
      for (line <- allDigits) {
        val value = line(currentIndex)
        if (value == 0)
          zeroes += 1
        else
          ones += 1
      }
      if (zeroes > ones)
        gammaRate += "0"
      else
        gammaRate += "1"
      currentIndex += 1
    }
    gammaRate = gammaRate.tail
    println(s"Gamma rate: $gammaRate, Epsilon rate: ")
    for (char <- gammaRate) {
      if (char == '1')
        print("0")
      else
        print("1")
    }
  } finally {
    file.close()
  }

}