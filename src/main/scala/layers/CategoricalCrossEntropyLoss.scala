package layers

import numscala.Numscala

object CategoricalCrossEntropyLoss {
    val MIN = 0.0000001
    val MAX = 1.0 - MIN

    def forward(yPred: Matrix, classTargets: List[Int]): Double = {
        val yPredClipped = Numscala.clip(yPred, MIN, MAX)

        // Probabilities for target values -
        // only if categorical labels
        val correctConfidences =
            for ((outputRow, index) <- yPredClipped.value.zip(classTargets))
            yield outputRow(index)
        val negLog = correctConfidences.map(-Math.log(_))
        negLog.sum / negLog.size
    }

    def forward(yPred: Matrix, yTrue: Matrix): Double = {
        val yPredClipped = Numscala.clip(yPred, MIN, MAX)

        // Mask values - only for one-hot encoded labels
        val correctConfidences =
            for ((outputRow, classTargetRow) <- yTrue.value.zip(yPredClipped.value))
                yield (for ((outputValue, classTargetValue) <- outputRow.zip(classTargetRow))
                    yield outputValue * classTargetValue).sum
        val negLog = correctConfidences.map(-Math.log(_))
        negLog.sum / negLog.size
    }
}
