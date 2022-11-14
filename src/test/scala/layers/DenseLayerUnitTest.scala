package layers

import numscala.SpiralData

class DenseLayerUnitTest {

    // Visual test of passing spiral data through a dense layer
    def main(args: Array[String]): Unit = {
        val sampleData = SpiralData.calculateDataMatrix()
        val dense1 = DenseLayer.apply(2, 3)
        val forwardValues = dense1.forward(sampleData)
        println(forwardValues)
    }
}