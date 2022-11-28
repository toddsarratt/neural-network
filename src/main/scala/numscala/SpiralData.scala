package numscala

import layers.Matrix
import numscala.Numscala.linspace

import scala.collection.mutable.ListBuffer
import scala.math.{cos, sin}
import scala.util.Random.nextGaussian

/**
 * From https://cs231n.github.io/neural-networks-case-study/
 * Generates coordinates which, when plotted, would resemble a spiral
 * Something about non-linear data for training
 */
object SpiralData {

    val POINTS_PER_CLASS = 100
    val CLASSES = 3
    val radii: List[Double] = linspace(0.0, 1, POINTS_PER_CLASS)

    // Returns a tuple. First element is a matrix of spiral data.
    // Second element is a List where each value at an index in the list is the class label for the
    // matrix row at the same index
    def calculateDataMatrix(): (Matrix, List[Int]) = {
        val classLabels = new ListBuffer[Int]()
        val oneListPerClass = for (classIndex <- Range(0, CLASSES)) yield {
            classLabels += classIndex
            val randomXsAndYs = linspace(classIndex * 4, (classIndex + 1) * 4, POINTS_PER_CLASS)
                .zip(List.fill(POINTS_PER_CLASS)(nextGaussian()).map(_ * 0.2))
            val theta = for ((x, y) <- randomXsAndYs)
                yield x + y
            val newXValues = zipWith(theta, radii, sin(_) * _)
            val newYValues = zipWith(theta, radii, cos(_) * _)
            for ((x, y) <- newXValues.zip(newYValues))
                yield List(x, y)
        }
        (Matrix.apply(oneListPerClass.fold(Nil)((x, y) => x ++ y)), classLabels.toList)
    }

    // How does Scala not have zipWith function?
    def zipWith(a: List[Double], b: List[Double], function: (Double, Double) => Double): List[Double] = {
        (a, b) match {
            case (Nil, _) | (_, Nil) => Nil
            case (x :: xs, y :: ys) => function(x, y) :: zipWith(xs, ys, function)
        }
    }
}
