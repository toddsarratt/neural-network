package layers

import numscala.SpiralData
import org.scalatest.funsuite.AnyFunSuite

class ReLUUnitTest extends AnyFunSuite {
    test("Verify ReLU function") {
        val inputs = List(0, 2, -1, 3.3, -2.7, 1.1, 2.2, -100)
        val expectedOutputs = List(0.0, 2.0, 0.0, 3.3, 0.0, 1.1, 2.2, 0.0)
        val testResults = ReLU.forward(inputs)
        assert(testResults == expectedOutputs)
    }

    test("Visual inspection of ReLU function") {
        val (sampleData, _) = SpiralData.calculateDataMatrix()
        val dense1 = DenseLayer.apply(2, 3)
        val forwardPassThruLayer = dense1.forward(sampleData)
        val forwardPassThruActivationFunction = ReLU.forward(forwardPassThruLayer)
        println(forwardPassThruActivationFunction)
    }
}
