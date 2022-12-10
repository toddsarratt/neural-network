package integrationtests

import layers.{Accuracy, CategoricalCrossEntropyLoss, DenseLayer, Matrix, ReLU, Softmax}
import numscala.Numscala.randn
import org.scalatest.funsuite.AnyFunSuite
import testsets.VerticalData

import scala.util.Random.nextGaussian

class IntroducingOptimization extends AnyFunSuite {

    test("Chapter Six") {
        val (x, y) = VerticalData.createData(samples = 100, classes = 3)
        val dense1 = DenseLayer.apply(2, 3)
        val dense2 = DenseLayer.apply(3, 3)

        var lowestLoss = 9999999.0
        var bestDense1Weights = dense1.weights
        var bestDense1Biases = dense1.biases
        var bestDense2Weights = dense2.weights
        var bestDense2Biases = dense2.biases

        for (iteration <- 0 until 10000) yield {

            dense1.weights = randn(0.05, 2, 3)
            dense1.biases = randn(0.05, 3)
            dense2.weights = randn(0.05, 3, 3)
            dense2.biases = randn(0.05, 3)

            val dense1Output = dense1.forward(x)
            val firstActivation = ReLU.forward(dense1Output)
            val dense2Output = dense2.forward(firstActivation)
            val secondActivation = Softmax.forward(dense2Output)

            val loss = CategoricalCrossEntropyLoss.forward(secondActivation, y)

            val accuracy = Accuracy.calculate(secondActivation, y)
            if (loss < lowestLoss) {
                println(
                    String.format("New set of weights found, iteration: %d loss: %f acc: %f",
                        iteration, loss, accuracy))
                bestDense1Weights = dense1.weights
                bestDense1Biases = dense1.biases
                bestDense2Weights = dense2.weights
                bestDense2Biases = dense2.biases
                lowestLoss = loss
            }
        }
    }

    test("Next iteration page 135") {
        val (x, y) = VerticalData.createData(samples = 100, classes = 3)
        val dense1 = DenseLayer.apply(2, 3)
        val dense2 = DenseLayer.apply(3, 3)

        var lowestLoss = 9999999.0
        var bestDense1Weights = dense1.weights
        var bestDense1Biases = dense1.biases
        var bestDense2Weights = dense2.weights
        var bestDense2Biases = dense2.biases

        for (iteration <- 0 until 10000000) yield {

            dense1.weights = Matrix.apply(
                dense1.weights.value.map(row =>
                    row.map(n => n + 0.05 * nextGaussian())
                )
            )
            dense1.biases = dense1.biases.map(n => n + 0.05 * nextGaussian())
            dense2.weights = Matrix.apply(
                dense2.weights.value.map(row =>
                    row.map(n => n + 0.05 * nextGaussian())
                )
            )
            dense2.biases = dense2.biases.map(n => n + 0.05 * nextGaussian())

            val dense1Output = dense1.forward(x)
            val firstActivation = ReLU.forward(dense1Output)
            val dense2Output = dense2.forward(firstActivation)
            val secondActivation = Softmax.forward(dense2Output)

            val loss = CategoricalCrossEntropyLoss.forward(secondActivation, y)

            val accuracy = Accuracy.calculate(secondActivation, y)
            if (loss < lowestLoss) {
                println(
                    String.format("New set of weights found, iteration: %d loss: %f acc: %f",
                        iteration, loss, accuracy))
                bestDense1Weights = dense1.weights
                bestDense1Biases = dense1.biases
                bestDense2Weights = dense2.weights
                bestDense2Biases = dense2.biases
                lowestLoss = loss
            } else {
                dense1.weights = bestDense1Weights
                dense1.biases = bestDense1Biases
                dense2.weights = bestDense2Weights
                dense2.biases = bestDense2Biases
            }
        }
    }
}
