package layers

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class SoftmaxUnitTest extends AnyFunSuite with Matchers {
    test("Verify Softmax function") {
        val inputs = List(4.8, 1.21, 2.385)
        val expectedOutput = List(0.89528066, 0.02470831, 0.08000903)
        val testResults = Softmax.calculate(inputs)
        testResults.sum should equal (1.0 +- 0.001)
        testResults.zip(expectedOutput).foreach {
            case (testResult, expectedResult) =>
                testResult should equal(expectedResult +- 0.001)
        }
    }
}
