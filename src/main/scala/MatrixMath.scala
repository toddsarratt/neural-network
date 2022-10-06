import scala.annotation.tailrec

/**
 * Is it all just linear algebra?
 */
object MatrixMath {

    def dotProduct(xOuter: List[Double], yOuter: List[Double]): Double = {
        @tailrec
        def dotProductWithAcc(xInner: List[Double], yInner: List[Double], acc: List[Double]): Double = {
            (xInner, yInner) match {
                case (_, Nil) | (Nil, _) => acc.sum
                case (x :: xs, y :: ys) => dotProductWithAcc(xs, ys, acc :+ (x * y))
            }
        }
        if (xOuter.length != yOuter.length)
            throw new IllegalArgumentException("Vectors must be the same length to compute dot product")
        else dotProductWithAcc(xOuter, yOuter, Nil)
    }

    def matrixProduct(xOuter: Matrix, yOuter: Matrix): Matrix = {
        @tailrec
        def matrixProductWithAcc(xInner: List[List[Double]], yInner: List[List[Double]], acc: List[List[Double]]): Matrix = {
            (xInner, yInner) match {
                case (Nil, _) => Matrix.apply(acc)
                case (_, _) =>
                    val newRow =
                        for (yRow <- yInner)
                            yield dotProduct(xInner.head, yRow)
                    matrixProductWithAcc(xInner.tail, yInner, acc :+ newRow)
            }
        }
        verifyMatrix(xOuter)
        verifyMatrix(yOuter)
        // Fuck vector columns. We'll transpose back if we have to
        matrixProductWithAcc(xOuter.value, transpose(yOuter).value, Nil)
    }

    def vectorAddition(matrix: Matrix, vector: List[Double]): Matrix = {
        verifyMatrix(matrix)
        if (matrix.value.head.length != vector.length)
            throw new IllegalArgumentException("Matrix and vector must have same width")
        val resultingMatrix = for (row <- matrix.value)
            yield {
                row.zip(vector).map {
                    case (matrixScalar, vectorScalar) =>
                        matrixScalar + vectorScalar
                }
            }
        Matrix.apply(resultingMatrix)
    }

    def transpose(y: Matrix): Matrix = {
        verifyMatrix(y)
        @tailrec
        def transposeWithAcc(xInner: List[List[Double]], acc: List[List[Double]], index: Int) : Matrix = {
            index match {
                case upperBound if upperBound >= xInner.head.length => Matrix.apply(acc)
                case _ =>
                    val yRow =
                        for (xRow <- xInner)
                            yield xRow(index)
                transposeWithAcc(xInner, acc :+ yRow, index + 1)
            }
        }
        transposeWithAcc(y.value, Nil, 0)
    }

    def verifyMatrix(matrix: Matrix): Unit = {
        matrix.value match {
            case Nil => throw new IllegalArgumentException("Cannot have NIL matrix")
            case internalLists =>
                val secondDimension = internalLists.head.length
                if(internalLists.exists(row => row.length != secondDimension)) {
                    throw new IllegalArgumentException("Matrix must be homogenous")
                }
        }
    }
}
