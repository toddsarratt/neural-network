package layers

object Softmax {

    def forward(inputs: List[Double]): List[Double] = {
        val maxValue = inputs.max
        val expValues = inputs
            .map(_ - maxValue) // Forces values into range -inf to 0 to prevent runaway exponentiation
            .map(normalize)
        val sum = expValues.sum
        expValues.map(_ / sum)
    }

    def normalize(input: Double): Double = {
        scala.math.pow(Math.E, input)
    }

    def forward(input: Matrix): Matrix = {
        Matrix.apply(input.value.map(forward))
    }
}
