package layers

import numscala.Numscala

object Accuracy {

    // Takes a matrix and a list of class targets
    // Iterates through the matrix and counts how many predictions (argmax) match the target for that "class"
    // Divides the number of "hits" by the total number of classes to provide an "accuracy"
    def calculate(outputs: Matrix, classTargets: List[Int]): Double = {
        val predictions = for (row <- outputs.value)
            yield Numscala.argmax(row)
        val matchingPredictions = predictions.zip(classTargets).collect {
            case (prediction, target) if prediction == target => true
        }
        matchingPredictions.size.doubleValue / classTargets.size.doubleValue
    }
}
