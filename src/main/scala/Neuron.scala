import scala.annotation.tailrec

class Neuron {
    var weights: List[Double] = Nil
    var bias: Double = 0.0

    def calculate(inputs: List[Double]): Double = {
        dotProduct(inputs).sum + bias
    }

    def dotProduct(inputs: List[Double]): List[Double] = {
        @tailrec
        def dotProductWithAcc(inputs: List[Double], weights: List[Double], acc: List[Double]): List[Double] = {
            (inputs, weights) match {
                case (Nil, Nil) => acc
                case (x :: xs, y :: ys) => dotProductWithAcc(xs, ys, acc :+ (x * y))
            }
        }
        if (this.weights.length != inputs.length) throw new IllegalArgumentException("Weights and inputs lengths differ")
        else dotProductWithAcc(inputs, this.weights, Nil)
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