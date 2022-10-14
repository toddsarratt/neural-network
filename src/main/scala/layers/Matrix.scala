package layers

import layers.MatrixMath.vectorAddition

class Matrix {
    var value: List[List[Double]] = Nil

    def plus(vector: List[Double]): Matrix = {
        vectorAddition(this, vector)
    }

    override def toString = s"Matrix($value)"
}

object Matrix {
    def apply(value: List[List[Double]]) : Matrix = {
        val matrix = new Matrix
        matrix.value = value
        matrix
    }
}
