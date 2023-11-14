fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0
        var forward = 0
        val operations = input.map { it.split(' ')}

        for ((direction, amountString) in operations) {
            val amount = amountString.toInt()

            when(direction) {
                "up"->depth-=amount
                "down"->depth+=amount
                "forward"->forward+=amount
            }
        }

        return depth * forward
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var forward = 0
        var aim = 0
        val operations = input.map { it.split(' ')}

        for ((direction, amountString) in operations) {
            val amount = amountString.toInt()

            when (direction) {
                "forward" -> run {
                    forward += amount
                    depth += aim * amount
                }
                "down" -> aim += amount
                "up" -> aim -= amount
            }
        }

        return depth * forward
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val res = part1(testInput)

    check(res == 150)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
