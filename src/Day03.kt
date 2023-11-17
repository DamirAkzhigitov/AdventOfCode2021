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
        fun calculateRating(type: RatingType): String {
            val columns = input[0].indices // [0, 1, 2, 3, 4, 5]
            var bitNumbers = input
            for (column in columns) {
                val (zeroes, ones) = bitNumbers.countBits(column)

                val mostCommon = if (zeroes > ones) '0' else '1'
                bitNumbers = bitNumbers.filter {
                    when (type) {
                        RatingType.OXYGEN -> it[column] == mostCommon
                        RatingType.CO2 -> it[column] != mostCommon
                    }
                }
                if (bitNumbers.size == 1) break
            }

            return bitNumbers.single()
        }
        val oxygenDecimal = calculateRating(RatingType.OXYGEN).toInt(2)
        val co2Decimal = calculateRating(RatingType.CO2).toInt(2)

        return oxygenDecimal * co2Decimal
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
private fun List<String>.countBits(column: Int): BitCount {
    var zeroes = 0
    var ones = 0
    for (line in this) {
        if (line[column] == '0') zeroes++ else ones++
    }
    return BitCount(zeroes, ones)
}

data class BitCount(val zeroes: Int, val ones: Int)
private enum class RatingType {
    OXYGEN,
    CO2
}