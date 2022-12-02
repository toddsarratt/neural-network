package numscala

import layers.Matrix

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
}