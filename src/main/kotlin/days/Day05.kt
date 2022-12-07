package days

/**
 * @see <a href="https://adventofcode.com/2022/day/5">Advent of Code Day 5</a>
 */
class Day05(lines: List<String>) : Day(lines) {
    override fun task1(): String {
        val input = splitInput()
        val listOfStacks = createListOfStacks(input["crates"])
        val instructions = sanitizeInstructions(input["instructions"])

        for (instruction in instructions) {
            var i = 0
            val from = Integer.parseInt(instruction[1]) - 1
            val to = Integer.parseInt(instruction[2]) - 1
            while (i < Integer.parseInt(instruction[0])) {
                listOfStacks[to].addLast(listOfStacks[from].removeLast())
                i++
            }
        }
        return getResult(listOfStacks)
    }

    override fun task2(): String {
        val input = splitInput()
        val listOfStacks = createListOfStacks(input["crates"])
        val instructions = sanitizeInstructions(input["instructions"])

        for (instruction in instructions) {
            var i = 0
            val from = Integer.parseInt(instruction[1]) - 1
            val to = Integer.parseInt(instruction[2]) - 1
            val stackToMove = arrayListOf<String>()
            while (i < Integer.parseInt(instruction[0])) {
                stackToMove.add(listOfStacks[from].removeLast())
                i++
            }
            // The Elements are pushed in reversed order, so it has to be undone again here.
            listOfStacks[to].addAll(stackToMove.reversed())
        }
        return getResult(listOfStacks)
    }

    // Only top Elements are needed.
    private fun getResult(listOfStacks: Array<ArrayDeque<String>>): String {
        var result = ""
        for (a in listOfStacks) {
            result += a.last()
        }

        return result
    }

    // The Input contains 2 parts this time.
    private fun splitInput(): Map<String, ArrayList<String>> {
        val crates = arrayListOf<String>()
        val instructions = arrayListOf<String>()
        var after = false
        for (line in lines) {
            if (line == "" || line.startsWith(" 1")) {
                after = true
                continue
            }
            if (after) {
                instructions.add(line)
            } else {
                crates.add(line)
            }
        }
        return mapOf("crates" to crates, "instructions" to instructions)
    }

    private fun sanitizeInstructions(instructions: List<String>?): ArrayList<List<String>> {
        val sanitizedInstructions = arrayListOf<List<String>>()
        if (instructions == null) {
            return sanitizedInstructions
        }
        for (line in instructions) {
            sanitizedInstructions.add(
                line.replace(" |move".toRegex(), "").replace("from|to".toRegex(), ",").split(",")
            )
        }
        return sanitizedInstructions
    }

    private fun createListOfStacks(crates: ArrayList<String>?): Array<ArrayDeque<String>> {
        // There's probably a better way to define this
        val listOfStacks = arrayOf<ArrayDeque<String>>(
            ArrayDeque(),
            ArrayDeque(),
            ArrayDeque(),
            ArrayDeque(),
            ArrayDeque(),
            ArrayDeque(),
            ArrayDeque(),
            ArrayDeque(),
            ArrayDeque()
        )
        if (crates == null) {
            return listOfStacks
        }
        // Reversed order because the lines go through the stacks from top to bottom element
        for (line in crates.reversed()) {
            var i = 0
            while (i < 9) {
                if (line.length < 2 + i * 4 || line.substring(1 + i * 4, 2 + i * 4) == " ") {
                    i++
                    continue
                }
                listOfStacks[i].add(line.substring(1 + i * 4, 2 + i * 4))
                i++
            }
        }
        return listOfStacks
    }
}