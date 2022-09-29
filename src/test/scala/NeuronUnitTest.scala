import org.scalatest.funsuite._

class NeuronUnitTest extends AnyFunSuite {
  test("Neuron calculation happy path") {
    val neuron = Neuron(List(0.2, 0.8, -0.5, 1.0), 2.0)
    val inputs = List(1.0, 2.0, 3.0, 2.5)
    assert(neuron.calculate(inputs) == 4.8)
  }
}