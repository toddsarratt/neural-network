import scala.annotation.tailrec

/**
 * Is it all just linear algebra?
 */
object MatrixMath {

    def dotProduct(xOuter: List[Double], yOuter: List[Double]): List[Double] = {
        @tailrec
        def dotProductWithAcc(xInner: List[Double], yInner: List[Double], acc: List[Double]): List[Double] = {
            (xInner, yInner) match {
                case (Nil, Nil) => acc
                case (x :: xs, y :: ys) => dotProductWithAcc(xs, ys, acc :+ (x * y))
            }
        }

        if (xOuter.length != yOuter.length) throw new IllegalArgumentException("Weights and inputs lengths differ")
        else dotProductWithAcc(xOuter, yOuter, Nil)
    }
}
