import scala.annotation.tailrec

/**
 * Is it all just linear algebra?
 */
object MatrixMath {

    def dotProduct(xOuter: List[Double], yOuter: List[Double]): List[Double] = {
        @tailrec
        def dotProductWithAcc(xInner: List[Double], yInner: List[Double], acc: List[Double]): List[Double] = {
            (xInner, yInner) match {
                case (_, Nil) | (Nil, _) => acc
                case (x :: xs, y :: ys) => dotProductWithAcc(xs, ys, acc :+ (x * y))
            }
        }

        if (xOuter.length != yOuter.length)
            throw new IllegalArgumentException("Vectors must be the same length to compute dot product")
        else dotProductWithAcc(xOuter, yOuter, Nil)
    }

    def matrixProduct(xOuter: List[List[Double]], yOuter: List[List[Double]]): List[List[Double]] = {

        @tailrec
        def matrixProductWithAcc(xInner: List[List[Double]], yInner: List[List[Double]], acc: List[List[Double]]): List[List[Double]] = {
            (xInner, yInner) match {
                case (Nil, _) => acc
                case (_, _) =>
                    val newRow =
                        for (yRow <- yInner)
                            yield dotProduct(xInner.head, yRow).sum
                    matrixProductWithAcc(xInner.tail, yInner, acc :+ newRow)
            }
        }
        verifyMatrix(xOuter)
        matrixProductWithAcc(xOuter, transpose(yOuter), Nil)
    }

    def transpose(y: List[List[Double]]): List[List[Double]] = {
        verifyMatrix(y)
        @tailrec
        def transposeWithAcc(xInner: List[List[Double]], acc: List[List[Double]], index: Int) : List[List[Double]] = {
            index match {
                case upperBound if upperBound >= xInner.head.length => acc
                case _ =>
                    val yRow =
                        for (xRow <- xInner)
                            yield xRow(index)
                transposeWithAcc(xInner, acc :+ yRow, index + 1)
            }
        }
        transposeWithAcc(y, Nil, 0)
    }

    def verifyMatrix(matrix: List[List[Double]]): Unit = {
        matrix match {
            case Nil => throw new IllegalArgumentException("Cannot have NIL matrix")
            case _ =>
                val secondDimension = matrix.head.length
                if(matrix.exists(row => row.length != secondDimension)) {
                    throw new IllegalArgumentException("Matrix must be homogenous")
                }
        }
    }
}
