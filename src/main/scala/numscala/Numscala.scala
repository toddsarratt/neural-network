package numscala

import layers.Matrix

import scala.util.Random.nextGaussian

object Numscala {

    // Creates a List from a to b of length elements
    def linspace(a: Double, b: Double, length: Int = 100): List[Double] = {
        val increment = (b - a) / (length - 1)
        List.tabulate(length)(i => a + increment * i)
    }

    // If a value in list is less than min, the value becomes min
    // If a value in list is greater than max, the value becomes max
    def clip(list: List[Double], min: Double, max: Double): List[Double] = {
        for (value <- list)
            yield
                if (value < min) min
                else if (value > max) max
                else value
    }

    // If a value in matrix is less than min, the value becomes min
    // If a value in matrix is greater than max, the value becomes max
    def clip(matrix: Matrix, min: Double, max: Double): Matrix = {
        Matrix.apply(
            matrix.value.map {
                row => clip(row, min, max)
            }
        )
    }

    // Finds max element in a list and returns its index
    def argmax(xs: List[Double]): Int = {
        xs.zipWithIndex.maxBy(_._1)._2
    }

    def randn(multiplier: Double, x: Int, y: Int): Matrix = {
        Matrix.apply(
            for (_ <- List.range(0, x)) yield
                for (_ <- List.range(0, y)) yield
                    multiplier * nextGaussian()
        )
    }

    def randn(multiplier: Double, x: Int): List[Double] = {
        for (_ <- List.range(0, x)) yield
            multiplier * nextGaussian()
    }

    def zipWith(a: List[Double], b: List[Double], function: (Double, Double) => Double): List[Double] = {
        (a, b) match {
            case (Nil, _) | (_, Nil) => Nil
            case (x :: xs, y :: ys) => function(x, y) :: zipWith(xs, ys, function)
        }
    }
}