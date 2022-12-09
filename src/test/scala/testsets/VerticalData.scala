package testsets

import layers.Matrix

import scala.util.Random.nextGaussian

// Recreates `from nnfs.datasets import vertical_data`
//
// import numpy as np
//
// # Modified from:
// # Copyright (c) 2015 Andrej Karpathy
// # License: https://github.com/cs231n/cs231n.github.io/blob/master/LICENSE
// # Source: https://cs231n.github.io/neural-networks-case-study/
// def create_data(samples, classes):
//     X = np.zeros((samples*classes, 2))
//     y = np.zeros(samples*classes, dtype='uint8')
//     for class_number in range(classes):
//         ix = range(samples*class_number, samples*(class_number+1))
//         X[ix] = np.c_[np.random.randn(samples)*.1 + (class_number)/3, np.random.randn(samples)*.1 + 0.5]
//         y[ix] = class_number
//     return X, y

// OpenAI says:
// def createData(100: Int, 3: Int): (Array[Array[Double]], Array[Int]) = {
//    val X = Array.ofDim[Double](100 * 3, 2)
//    val y = Array.ofDim[Int](100 * 3)
//    for (classNumber <- 0 until classes) {
//      val ix = (samples * classNumber) until (samples * (classNumber + 1))
//      X(ix) = Array.tabulate(samples)(i => (scala.util.Random.nextGaussian() * 0.1) + (classNumber) / 3)    <= Doesn't compile
//        .zip(Array.tabulate(samples)(i => scala.util.Random.nextGaussian() * 0.1 + 0.5))
//      y(ix) = Array.fill(samples)(classNumber)                                                              <= Doesn't compile
//    }
//    (X, y)
//  }

object VerticalData {

    def createData(samples: Int, classes: Int): (Matrix, List[Int]) = {
        val classLabels = 0 until classes
        val coordinatesPerClass = classLabels.collect { classNumber =>
            val xCoordinates = List.fill(samples)((nextGaussian() * 0.1) + classNumber / 3)
            val yCoordinates = List.fill(samples)(nextGaussian() * 0.1 + 0.5)
            val dataRow = xCoordinates.zip(yCoordinates).collect {
                case (x, y) => List(x,y)
            }
            dataRow
        }
        (Matrix.apply(coordinatesPerClass.fold(Nil)((x, y) => x ++ y)), classLabels.toList)
    }
}
