package numscala

import numscala.SpiralData.calculateDataMatrix

object SpiralDataVisualTest {

    // Visual Test
    def main(args: Array[String]): Unit = {
        val answer = calculateDataMatrix()
        println(answer.value.size)
    }
}
