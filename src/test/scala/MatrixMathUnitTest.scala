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
}
