package days

/**
 * @see <a href="https://adventofcode.com/2022/day/1">Advent of Code Day 1</a>
 */
class Day01(lines: List<String>) : Day(lines) {
    override fun task1(): String {
        return addGnomeCaloriesAndSort()[0].toString()
    }

    override fun task2(): String {
        val result = addGnomeCaloriesAndSort()
        return (result[0] + result[1] + result[2]).toString()
    }

    private fun addGnomeCaloriesAndSort(): ArrayList<Int> {
        val result = arrayListOf<Int>()
        var temp = 0
        for (line in lines) {
            if (line.isEmpty()) {
                result.add(temp)
                temp = 0
            } else {
                temp += Integer.parseInt(line)
            }
        }
        result.sortDescending()
        return result
    }
}