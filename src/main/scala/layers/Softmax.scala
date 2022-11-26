package layers

object Softmax {

    def calculate(inputs: List[Double]): List[Double] = {
        val normalizedInputs = inputs.map(normalize)
        val sum = normalizedInputs.sum
        normalizedInputs.map(_ / sum)
    }

    def normalize(input: Double): Double = {
        scala.math.pow(Math.E, input)
    }
}
