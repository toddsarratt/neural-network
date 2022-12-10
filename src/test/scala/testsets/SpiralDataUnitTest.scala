package testsets

import org.scalatest.funsuite.AnyFunSuite
import testsets.SpiralData.calculateDataMatrix

class SpiralDataUnitTest extends AnyFunSuite {
   test ("Verify spiral data size") {
       val (spiralData, labels) = calculateDataMatrix()
       assert(spiralData.value.size == 300)
       assert(labels.size == 300)
       println((spiralData, labels))
   }
}
