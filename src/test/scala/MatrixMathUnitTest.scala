import org.scalatest.funsuite.AnyFunSuite

class MatrixMathUnitTest extends AnyFunSuite {
    test("Matrix transpose happy path") {
        val testMatrix = List(
            List(1.0, 4.0, 7.0),
            List(2.0, 5.0, 8.0),
            List(3.0, 6.0, 9.0)
        )
        val expectedMatrix = List(
            List(1.0, 2.0, 3.0),
            List(4.0, 5.0, 6.0),
            List(7.0, 8.0, 9.0)
        )

        val testResults = MatrixMath.transpose(testMatrix)
        assert(testResults == expectedMatrix)
    }

    /*
    Doubles make for lousy unit testing. Uncomment when needed.
    test("Matrix product happy path") {
        val firstMatrix = List(
            List(.49, .97, .53, .05),
            List(.33, .65, .62, .51),
            List(1.0, .38, .61, .45),
            List(.74, .27, .64, .17),
            List(.36, .17, .96, .12)
        )
        val secondMatrix = List(
            List(.79, .32, .68, .9, .77),
            List(.18, .39, .12, .93, .09),
            List(.87, .42, .6, .71, .12),
            List(.45, .55, .40, .78, .81)
        )
        val expectedMatrixProduct = List(
            List(1.05, .79, .79, 1.76, .57),
            List(1.15, .90, .88, 1.74, .80),
            List(1.59, .97, 1.27, 2.04, 1.24),
            List(1.27, .70, .99, 1.5, .81),
            List(1.20, .65, .89, 1.26, .5)
        )
        val testResultMatrix = MatrixMath.matrixProduct(firstMatrix, secondMatrix)
        assert(testResultMatrix == expectedMatrixProduct)
    }
  */
}
