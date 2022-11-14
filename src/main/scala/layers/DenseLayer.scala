package layers

import layers.MatrixMath.matrixProduct

import scala.util.Random.nextGaussian

class DenseLayer {
    var weights: Matrix = Matrix.apply(Nil)
    var biases: List[Double] = Nil

    // Forward pass
    val forward: Matrix => Matrix = (inputs: Matrix) => {
        matrixProduct(inputs, weights) plus biases
    }

    override def toString = s"DenseLayer($weights, $biases)"
}

object DenseLayer {
    val magnitude = 0.01
    def apply(numberOfInputs: Int, numberOfNeurons: Int): DenseLayer = {
        val denseLayer = new DenseLayer
        denseLayer.weights = generateWeights(numberOfInputs, numberOfNeurons)
        denseLayer.biases = generateBiases(numberOfNeurons)
        denseLayer
    }

    def generateWeights(numberOfInputs: Int, numberOfNeurons: Int): Matrix = {
        val weights = for (i <- (0 until numberOfInputs).toList)
            yield List.fill(numberOfNeurons)(nextGaussian()).map(_ * magnitude)
        Matrix.apply(weights)
    }

    def generateBiases(numberOfNerons: Int): List[Double] = {
        List.fill(numberOfNerons)(0)
    }
}
