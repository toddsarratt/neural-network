package layers

import numscala.Numscala

object Accuracy {

    def calculate(outputs: Matrix, classTargets: List[Int]): Double = {
        val predictions = for (row <- outputs.value)
            yield Numscala.argmax(row)
        val matchingPredictions = predictions.zip(classTargets).collect {
            case (prediction, target) if prediction == target => true
        }
        matchingPredictions.size.doubleValue / classTargets.size.doubleValue
    }
}
