package numscala

import numscala.SpiralData.calculateDataMatrix
import org.scalatest.funsuite.AnyFunSuite

class SpiralDataVisualTest extends AnyFunSuite {
   test ("Verify spiral data size") {
        val (spiralData, labels) = calculateDataMatrix()
       assert(spiralData.value.size == 300)
       assert(labels.size == 3)
    }
}
