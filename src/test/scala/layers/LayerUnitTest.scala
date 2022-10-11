package layers

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class LayerUnitTest extends AnyFunSuite with Matchers {
        val inputs: Matrix = Matrix.apply(
            List(
                List(1.0, 2.0, 3.0, 2.5),
                List(2.0, 5.0, -1.0, 2.0),
                List(-1.5, 2.7, 3.3, -0.8)
            )
        )
        val weights: Matrix = Matrix.apply(
            List(
                List(0.2, 0.8, -0.5, 1.0),
                List(0.5, -0.91, 0.26, -0.5),
                List(-0.26, -0.27, 0.17, 0.87)
            )
        )
        val biases: List[Double] = List(2.0, 3.0, 0.5)
        val weights2: Matrix = Matrix.apply(
            List(
                List(0.1, -0.14, 0.5),
                List(-0.5, 0.12, -0.33),
                List(-0.44, 0.73, -0.13)
            )
        )
        val biases2: List[Double] = List(-1, 2, -0.5)
        val layer1Outputs: Matrix = Matrix.apply(
            List(
                List(4.8, 1.21, 2.385),
                List(8.9, -1.81, 0.2),
                List(1.41, 1.051, 0.026)
            )
        )
        val layer2Outputs: Matrix = Matrix.apply(
            List(
                List(0.5031, -1.04185, -2.03875),
                List(0.2434, -2.7332, -5.7633),
                List(-0.99314, 1.41254, -0.35655)
            )
        )
    test("layers.Layer calculation happy path") {
        val testLayer = Layer.apply(weights, biases)
        val testCalculation = testLayer.calculate(inputs)
        compareAndAssertMatrixApproximation(testCalculation, layer1Outputs)
    }

    test("Two layer calculation happy path") {
        val testLayer = Layer.apply(weights, biases)
        val testLayer2 = Layer.apply(weights2, biases2)
        val testCalculation = (testLayer.calculate andThen testLayer2.calculate)(inputs)
        compareAndAssertMatrixApproximation(testCalculation, layer2Outputs)
    }

    def compareAndAssertMatrixApproximation(testMatrix: Matrix, expectedMatrix: Matrix): Unit = {
        testMatrix.value.zip(expectedMatrix.value).foreach {
            case (testRow, expectedRow) =>
                testRow.zip(expectedRow).foreach {
                    case (testResult, expectedResult) =>
                        testResult should equal(expectedResult +- 0.005)
                }
        }
    }
}
