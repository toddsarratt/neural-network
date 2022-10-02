import MatrixMath.dotProduct

class Neuron {
    var weights: List[Double] = Nil
    var bias: Double = 0.0

    def calculate(inputs: List[Double]): Double = {
        dotProduct(inputs, weights).sum + bias
    }
}

object Neuron {
    def apply(weights: List[Double], bias: Double): Neuron = {
        val neuron = new Neuron
        neuron.weights = weights
        neuron.bias = bias
        neuron
    }
}