import MatrixMath.{dotProduct, matrixProduct, transpose}

class Layer {
    var weights: Matrix = Matrix.apply(Nil)
    var biases: List[Double] = Nil

    def neuronCalculations(inputs: Matrix): Matrix = {
        matrixProduct(inputs, transpose(weights)) plus biases
    }

    override def toString = s"Layer($weights, $biases)"
}

object Layer {
    def apply(weights: Matrix, biases: List[Double]): Layer = {
        val layer = new Layer
        layer.weights = weights
        layer.biases = biases
        layer
    }
}
