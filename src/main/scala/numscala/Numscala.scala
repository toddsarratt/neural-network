package numscala

object Numscala {

    def linspace(a: Double, b: Double, length: Int = 100): List[Double] = {
        val increment = (b - a) / (length - 1)
        List.tabulate(length)(i => a + increment * i)
    }

}
