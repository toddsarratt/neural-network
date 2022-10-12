package numscala

import layers.Matrix
import numscala.Numscala.linspace

import scala.math.{cos, sin}
import scala.util.Random.nextGaussian

/**
 * From https://cs231n.github.io/neural-networks-case-study/
 *
 * X[ix] = np.c_[r*np.sin(t), r*np.cos(t)]
 * y[ix] = j
 */
object SpiralData {

    val N = 100 // number of points per class
    val D = 2 // dimensionality
    val K = 3 // number of classes
    val radii: List[Double] = linspace(0.0, 1, N)

    def calculateDataMatrix(): Matrix = {
        val threeLists = for (j <- Range(0, K)) yield {
            val s = linspace(j * 4, (j + 1) * 4, N).zip(List.fill(N)(nextGaussian()).map(_ * 0.2))
            val t = for ((x, y) <- s)
                yield x + y
            // radii is a LIST OF DOUBLES might be the issue
            val newXValues = zipWith(t, radii, sin(_) * _)
            val newYValues = zipWith(t, radii, cos(_) * _)
                for ((x, y) <- newXValues.zip(newYValues))
                    yield List(x, y)
        }
        Matrix.apply(threeLists.fold(Nil)((x, y) => x ++ y))
    }

    def zipWith(a: List[Double], b: List[Double], function: (Double, Double) => Double) : List[Double] = {
        (a, b) match {
            case (Nil, _) | (_, Nil) => Nil
            case (x :: xs, y :: ys) => function(x, y) :: zipWith(xs, ys, function)
        }
    }

    def main(args: Array[String]): Unit = {
        val answer = calculateDataMatrix()
        println(answer.value.size)
    }
}
