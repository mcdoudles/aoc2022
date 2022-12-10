package days

/**
 * @see <a href="https://adventofcode.com/2022/day/10">Advent of Code Day 10</a>
 */
class Day10(lines: List<String>) : Day(lines) {
    override fun task1(): String {
        val splitted = lines.map { line -> line.split(" ") }
        val state = State(0, 1)
        var result = 0
        for (line in splitted) {
            result += state.increaseCycle()
            when (line[0]) {
                "addx" -> {
                    result += state.increaseCycle()
                    state.addToRegister(line[1].toInt())
                }
            }

        }
        return result.toString()
    }


    override fun task2(): String {
        val splitted = lines.map { line -> line.split(" ") }
        val state = State(0, 1)
        val result = StringBuffer()
        for (line in splitted) {
            state.increaseCycle()
            result.append(state.getScreenLine())
            when (line[0]) {
                "addx" -> {
                    state.increaseCycle()
                    result.append(state.getScreenLine())
                    state.addToRegister(line[1].toInt())
                }
            }

        }
        return result.toString()
    }

    class State(private var cycle: Int, private var register: Int) {
        fun addToRegister(added: Int) {
            this.register += added
        }

        fun increaseCycle(): Int {
            var retVal = 0
            this.cycle++
            if (listOf(20, 60, 100, 140, 180, 220).contains(cycle)) {
                retVal = register * cycle
            }
            return retVal
        }

        fun getScreenLine(): StringBuffer {
            val tempBuffer = StringBuffer()

            tempBuffer.append(
                if (listOf(
                        register - 1,
                        register,
                        register + 1
                    ).contains((cycle - 1) % 40)
                ) "##" else "  "
            )

            if (listOf(40, 80, 120, 160, 200, 240).contains(cycle)) {
                tempBuffer.append("\n")
            }
            return tempBuffer
        }
    }
}