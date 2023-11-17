fun main() {
    fun part1(input: List<String>): Int {
        var resultList = mutableListOf<Int>()
        var counter: Int = 0

        for (item in input) {
            val bitNumber = item.split("").filter { it !== "" }.map { it.toInt() }

            if (counter == 0) {
                resultList = bitNumber.toMutableList()
            } else {
                for (index in 0..bitNumber.lastIndex) {
                    resultList[index] += bitNumber[index]
                }
            }

            counter += 1
        }

        val halfSize: Double = counter.toDouble() / 2

        for (index in 0..resultList.lastIndex) {
            if (resultList[index] > halfSize) {
                resultList[index] = 1
            } else {
                resultList[index] = 0
            }
        }

        val gamma = resultList.joinToString("")
        val epsilon  = resultList.map { if (it == 0) 1 else 0 }.joinToString("")

        val gammaDecimal = gamma.toInt(2)
        val epsilonDecimal = epsilon.toInt(2)

        return gammaDecimal * epsilonDecimal
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val res = part1(testInput)

    println("res: $res")

    check(res == 198)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
