package layers

import layers.MatrixMath.{matrixProduct, transpose}

class Layer {
    var weights: Matrix = Matrix.apply(Nil)
    var biases: List[Double] = Nil

    val calculate: Matrix => Matrix = (inputs: Matrix) => {
        matrixProduct(inputs, transpose(weights)) plus biases
    }

    override def toString = s"layers.Layer($weights, $biases)"
}

object Layer {
    def apply(weights: Matrix, biases: List[Double]): Layer = {
        val layer = new Layer
        layer.weights = weights
        layer.biases = biases
        layer
    }
}
