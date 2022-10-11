package layers

import layers.MatrixMath.transpose
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class MatrixMathUnitTest extends AnyFunSuite with Matchers {
    test("layers.Matrix transpose happy path") {
        val testMatrix = Matrix.apply(
            List(
                List(1.0, 4.0, 7.0),
                List(2.0, 5.0, 8.0),
                List(3.0, 6.0, 9.0)
            )
        )
        val expectedMatrix = Matrix.apply(
            List(
                List(1.0, 2.0, 3.0),
                List(4.0, 5.0, 6.0),
                List(7.0, 8.0, 9.0)
            )
        )

        val testResults = MatrixMath.transpose(testMatrix)
        compareAndAssertMatrixApproximation(testResults, expectedMatrix)
    }

    test("layers.Matrix product happy path") {
        val firstMatrix = Matrix.apply(
            List(
                List(.49, .97, .53, .05),
                List(.33, .65, .62, .51),
                List(1.0, .38, .61, .45),
                List(.74, .27, .64, .17),
                List(.36, .17, .96, .12)
            )
        )
        val secondMatrix = Matrix.apply(
            List(
                List(.79, .18, .87, .45),
                List(.32, .39, .42, .55),
                List(.68, .12, .6, .40),
                List(.9, .93, .71, .78),
                List(.77, .09, .12, .81)
            )
        )
        val expectedMatrixProduct = Matrix.apply(
            List(
                List(1.05, .79, .79, 1.76, .57),
                List(1.15, .90, .88, 1.74, .80),
                List(1.59, .97, 1.27, 2.04, 1.24),
                List(1.27, .70, .99, 1.5, .81),
                List(1.20, .65, .89, 1.26, .5)
            )
        )
        val testResultMatrix = MatrixMath.matrixProduct(firstMatrix, transpose(secondMatrix))
        compareAndAssertMatrixApproximation(testResultMatrix, expectedMatrixProduct)
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
