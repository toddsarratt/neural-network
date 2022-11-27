package numscala

import layers.Matrix

object Numscala {

    def linspace(a: Double, b: Double, length: Int = 100): List[Double] = {
        val increment = (b - a) / (length - 1)
        List.tabulate(length)(i => a + increment * i)
    }

    def clip(list: List[Double], min: Double, max: Double): List[Double] = {
        for (value <- list)
            yield
                if (value < min) min
                else if (value > max) max
                else value
    }

    def clip(matrix: Matrix, min: Double, max: Double): Matrix = {
        Matrix.apply(
            matrix.value.map {
                row => clip(row, min, max)
            }
        )
    }
}
