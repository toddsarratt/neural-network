package testsets

import layers.Matrix

import scala.collection.mutable.ListBuffer
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


object VerticalData {

    def createData(samples: Int, classes: Int): (Matrix, List[Int]) = {
        val classLabels = new ListBuffer[Int]()
        val coordinatesPerClass = for (classIndex <- 0 until classes) yield {
            val xCoordinates = List.fill(samples)((nextGaussian() * 0.1) + classIndex / 3)
            val yCoordinates = List.fill(samples)(nextGaussian() * 0.1 + 0.5)
            for ((x, y) <- xCoordinates.zip(yCoordinates)) yield {
                classLabels += classIndex
                List(x, y)
            }
        }
        (Matrix.apply(coordinatesPerClass.fold(Nil)((x, y) => x ++ y)), classLabels.toList)
    }
}
