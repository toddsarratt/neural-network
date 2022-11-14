package layers

object ReLU {

    def forward(inputs: List[Double]): List[Double] = {
        inputs.map(x => Math.max(0, x))
    }

    def forward(input: Matrix): Matrix = {
        Matrix.apply(
            input.value.map(row => {
                row.map(x => Math.max(0, x))
            })
        )
    }
}
