import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class LayerUnitTest extends AnyFunSuite with Matchers {
    test("Layer calculations happy path") {
        val testInputs = Matrix.apply(
            List(
                List(1.0, 2.0, 3.0, 2.5),
                List(2.0, 5.0, -1.0, 2.0),
                List(-1.5, 2.7, 3.3, -0.8)
            )
        )
        val testWeights = Matrix.apply(
            List(
                List(0.2, 0.8, -0.5, 1.0),
                List(0.5, -0.91, 0.26, -0.5),
                List(-0.26, -0.27, 0.17, 0.87)
            )
        )
        val testBiases = List(2.0, 3.0, 0.5)
        val expectedCalculations = Matrix.apply(
            List(
                List(4.8, 1.21, 2.385),
                List(8.9, -1.81, 0.2),
                List(1.41, 1.051, 0.026)
            )
        )
        val testLayer = Layer.apply(testWeights, testBiases)
        val testCalculation = testLayer.neuronCalculations(testInputs)
        compareAndAssertMatrixApproximation(testCalculation, expectedCalculations)
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
