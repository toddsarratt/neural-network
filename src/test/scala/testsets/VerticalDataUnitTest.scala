package testsets

import org.scalatest.funsuite.AnyFunSuite

class VerticalDataUnitTest extends AnyFunSuite {
   test ("Verify verical data size") {
        val (spiralData, labels) = VerticalData.createData(100, 3)
       assert(spiralData.value.size == 300)
       assert(labels.size == 3)
    }
}
