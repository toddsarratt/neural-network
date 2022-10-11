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
    val r: List[Double] = linspace(0.0,1,N) // radius

    def calculateDataMatrix(): Matrix = {
        for (j <- Range(0, K)) yield {
            val s = linspace(j * 4, (j + 1) * 4, N).zip(List.fill(N)(nextGaussian()).map(_ * 0.2))
            val t = for ((x, y) <- s)
                yield x + y
            val newPoints: Tuple2 = t.map(a => sin(a) * r).zip(t.map(a => cos(a) * r))
            return Matrix.apply(
                newPoints.map { point => List(point._1, point._2) }
            )
        }
    }
}
