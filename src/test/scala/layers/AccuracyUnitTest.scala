package layers

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class AccuracyUnitTest extends AnyFunSuite with Matchers {
    test("Accuracy calculation happy path") {
        val testOutputs = Matrix.apply(List(
            List(0.7, 0.2, 0.1),   // prediction = 0
            List(0.5, 0.1, 0.4),   // prediction = 0 doesn't match class target of 1
            List(0.02, 0.9, 0.08)  // prediction = 1
        ))
        val classTargets = List(0, 1, 1) // 2nd class target of 1 doesn't match 2nd row prediction of 0
        val testResult = Accuracy.calculate(testOutputs, classTargets)  // should be 2/3
        testResult should equal(2.0/3.0 +- 0.001)
    }
    test("Accuracy calculation happy path") {
        val testOutputs = Matrix.apply(List(
            List(0.7, 0.2, 0.1),   // prediction = 0
            List(0.5, 0.1, 0.4),   // prediction = 0 doesn't match class target of 1
            List(0.02, 0.9, 0.08)  // prediction = 1
        ))
        val classTargets = List(0, 1, 1) // 2nd class target of 1 doesn't match 2nd row prediction of 0
        val testResult = Accuracy.calculate(testOutputs, classTargets)  // should be 2/3
        testResult should equal(2.0/3.0 +- 0.001)
    }
}
