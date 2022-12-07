package days

/**
 * @see <a href="https://adventofcode.com/2022/day/6">Advent of Code Day 6</a>
 */
class Day06(lines: List<String>) : Day(lines) {
    override fun task1(): String {
        return findUniqueStringWithLength(4).toString()
    }

    override fun task2(): String {
        return findUniqueStringWithLength(14).toString()
    }

    private fun String.allUnique(): Boolean = all(hashSetOf<Char>()::add)

    private fun findUniqueStringWithLength(length: Int): Int {
        // Just one line this time
        val line = lines[0]
        for (i in 0..line.length - length) {
            if (line.substring(i, i + length).allUnique()) {
                return (i + length)
            }
        }
        throw Exception("No result found.")
    }
}