package layers

import numscala.SpiralData
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class SoftmaxUnitTest extends AnyFunSuite with Matchers {
    test("Verify Softmax function rows each sum to 1") {
        val inputs = List(4.8, 1.21, 2.385)
        val expectedOutput = List(0.89528066, 0.02470831, 0.08000903)
        val testResults = Softmax.forward(inputs)
        testResults.sum should equal (1.0 +- 0.001)
        testResults.zip(expectedOutput).foreach {
            case (testResult, expectedResult) =>
                testResult should equal(expectedResult +- 0.001)
        }
    }

    test("Spiral Data with Softmax rows each sum to 1") {
        val testSpiralData = SpiralData.calculateDataMatrix()
        val dense1 = DenseLayer.apply(2, 3)
        val dense2 = DenseLayer.apply(3, 3)
        val dense1Forward = dense1.forward(testSpiralData)
        val reluActivationValue = ReLU.forward(dense1Forward)
        val dense2Forward = dense2.forward(reluActivationValue)
        val softmaxActivationValue = Softmax.forward(dense2Forward)
        softmaxActivationValue.value.foreach {row =>
            row.sum should equal (1.00 +- 0.0001)
        }
        println(softmaxActivationValue)
    }
}
