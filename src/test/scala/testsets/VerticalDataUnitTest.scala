package testsets

import org.scalatest.funsuite.AnyFunSuite

class VerticalDataUnitTest extends AnyFunSuite {
    test("Verify vertical data size") {
        val (verticalData, labels) = VerticalData.createData(100, 3)
        assert(verticalData.value.size == 300)
        assert(labels.size == 300)
        println(verticalData)
        println(labels)
    }
}
