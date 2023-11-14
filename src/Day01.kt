fun main() {
    fun part1(input: List<Int>): Int {
        val windowed = input.windowed(2)

        return windowed.count { (a,b) -> b > a }
    }

    fun part2(input: List<Int>): Int {
        val windowed = input.windowed(4)

        return windowed.count { it[0] < it[3] }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsInts("Day01_test")
    val res = part1(testInput)

    check(res == 7)

    val input = readInputAsInts("Day01")
    part1(input).println()
    part2(input).println()
}
