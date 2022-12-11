package days

/**
 * @see <a href="https://adventofcode.com/2022/day/11">Advent of Code Day 11</a>
 */
class Day11(lines: List<String>) : Day(lines) {
    override fun task1(): String {
        val monkeys = getInitialMonkeys()
        repeat(20) {
            roundOfInspects(monkeys) { x -> x / 3 }
        }
        return monkeys.business().toString()
    }

    override fun task2(): String {
        val monkeyList = getInitialMonkeys()
        val idk: Long = monkeyList.map { it.divisor }.reduce(Long::times)
        repeat(10000) {
            roundOfInspects(monkeyList) { x -> x % idk }
        }
        return monkeyList.business().toString()
    }

    private fun getInitialMonkeys(): List<Monkey> {
        var i = 0
        val monkeyList = mutableListOf<Monkey>()
        while (i < lines.size) {
            if (lines[i].contains("Monkey")) {
                val items = lines[i + 1].substringAfter(": ").split(", ").map(String::toLong)
                val operation = lines[i + 2].substringAfter("= ").split(" ")
                val divisor = lines[i + 3].substringAfterLast(" ").toLong()
                val trueMonkey = lines[i + 4].substringAfterLast(" ").toInt()
                val falseMonkey = lines[i + 5].substringAfterLast(" ").toInt()
                monkeyList += Monkey(items.toMutableList(), divisor, trueMonkey, falseMonkey) { old ->
                    val firstOperant = if (operation[0] == "old") old else operation[0].toLong()
                    val secondOperant = if (operation[2] == "old") old else operation[2].toLong()
                    if (operation[1] == "+") {
                        firstOperant + secondOperant
                    } else {
                        firstOperant * secondOperant
                    }
                }
                i += 5
            }
            i++
        }
        return monkeyList
    }

    private fun roundOfInspects(monkeyList: List<Monkey>, worryFunction: (Long) -> Long) {
        monkeyList.forEach {
            it.inspectItems(monkeyList, worryFunction)
        }
    }

    class Monkey(
        private val items: MutableList<Long>,
        val divisor: Long,
        private val trueMonkey: Int,
        private val falseMonkey: Int,
        val operation: (Long) -> Long
    ) {
        var interactions = 0L
        fun inspectItems(monkeyList: List<Monkey>, worryFunction: (Long) -> Long) {
            items.forEach { item ->
                val newItem = worryFunction(operation(item))
                val nextMonkey = getNextMonkey(newItem)
                monkeyList[nextMonkey].items.add(newItem)
            }
            interactions += items.size
            items.clear()
        }

        private fun getNextMonkey(item: Long): Int {
            return if (item % divisor == 0L) trueMonkey else falseMonkey
        }
    }

    private fun List<Monkey>.business(): Long = sortedByDescending { it.interactions }
        .let { (first, second) -> first.interactions * second.interactions }
}