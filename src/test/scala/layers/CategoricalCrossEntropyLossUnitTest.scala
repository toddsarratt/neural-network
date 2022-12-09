package layers

import numscala.SpiralData
import org.scalactic.Tolerance.convertNumericToPlusOrMinusWrapper
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

class CategoricalCrossEntropyLossUnitTest extends AnyFunSuite {
    test("CategoricalCrossEntropyLoss happy path") {
        val softmaxOutputs = Matrix.apply(
            List(
                List(0.7, 0.1, 0.20),
                List(0.1, 0.5, 0.4),
                List(0.02, 0.9, 0.08)
        ))
        val classTargets = Matrix.apply(
            List(
                List(1, 0, 0),
                List(0, 1, 0),
                List(0, 1, 0)
            )
        )
        val testOutput = CategoricalCrossEntropyLoss.forward(softmaxOutputs, classTargets)
        testOutput should equal (0.38506 +- 0.00001)
    }

    test("CategoricalCrossEntropyLoss spiral data happy path") {
        val (spiralData, classLabels) = SpiralData.calculateDataMatrix()
        val dense1 = DenseLayer(2, 3)
        val dense2 = DenseLayer(3, 3)
        val firstPass = dense1.forward(spiralData)
        val firstActivation = ReLU.forward(firstPass)
        val secondPass = dense2.forward(firstActivation)
        val secondActivation = Softmax.forward(secondPass)
        println(secondActivation)
        val expectedLoss = 1.09861
        val testLoss = CategoricalCrossEntropyLoss.forward(secondActivation, classLabels)
        testLoss should equal (expectedLoss +- 0.00001)
    }
}
