package numscala

import layers.Matrix
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class NumscalaUnitTest extends AnyFunSuite with Matchers {
    test("Verify clip function for vector") {
        val min = -100.0
        val max = 100.0
        val testInput = List(Double.MinValue, -0.001, -0.1, 0.0, 1.0, 1000.0, Double.MaxValue)
        val expectedOutput = List(min, -0.001, -0.1, 0.0, 1.0, max, max)
        val testResult = Numscala.clip(testInput, min, max)
        testResult.zip(expectedOutput).foreach{
            case (testResult, expectedResult) =>
                testResult should equal(expectedResult +- 0.001)
        }
    }
    test("Verify clip function for matrix") {
        val min = -100.0
        val max = 100.0
        val testInput = Matrix.apply(
            List(
                List(Double.MinValue, -0.001, -0.1, 0.0, 1.0, 1000.0, Double.MaxValue),
                List(Double.MinValue, -0.001, -0.1, 0.0, 1.0, 1000.0, Double.MaxValue),
                List(Double.MinValue, -0.001, -0.1, 0.0, 1.0, 1000.0, Double.MaxValue)
            ))
        val expectedOutput = List(min, -0.001, -0.1, 0.0, 1.0, max, max)
        val testResult = Numscala.clip(testInput, min, max)
        testResult.value.foreach { row =>
            row.zip(expectedOutput).foreach {
                case (testResult, expectedResult) =>
                    testResult should equal(expectedResult +- 0.001)
            }
        }
    }

    test("argmax happy path") {
        assert(Numscala.argmax(List(0.0, 0.5, 1.0)) == 2)
        assert(Numscala.argmax(List(0.0, 1.0, 0.0)) == 1)
        assert(Numscala.argmax(List(1.0, 0.5, 0.0)) == 0)
    }
}
