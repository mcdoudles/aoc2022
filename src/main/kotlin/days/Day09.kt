package days

import kotlin.math.abs

/**
 * @see <a href="https://adventofcode.com/2022/day/9">Advent of Code Day 9</a>
 */
class Day09(lines: List<String>) : Day(lines) {
    override fun task1(): String {
        return moveRope(1).toString()
    }

    override fun task2(): String {
        return moveRope(9).toString()
    }

    private fun moveRope(tailLength: Int): Int {
        var head = Position(0, 0)
        val tails = mutableListOf<Position>()

        var i = tailLength
        while (i > 0) {
            tails += Position(0, 0)
            i--
        }

        val visited = mutableSetOf<Position>()
        visited += head

        val movements = lines.map { line -> Movement(line.split(" ")[0], Integer.parseInt(line.split(" ")[1])) }
        for (movement in movements) {
            while (movement.steps > 0) {
                head = moveHead(head, movement.direction)
                var target = head
                var j = 0
                while (j < tailLength) {
                    tails[j] = moveTail(tails[j], target)
                    target = tails[j]
                    j++
                }
                visited.add(tails[tailLength - 1])
                movement.steps--
            }
        }
        return visited.size
    }

    private fun moveHead(head: Position, direction: String): Position {
        return when (direction) {
            "U" -> Position(head.x, head.y + 1)
            "D" -> Position(head.x, head.y - 1)
            "R" -> Position(head.x + 1, head.y)
            "L" -> Position(head.x - 1, head.y)
            else -> return head
        }
    }

    private fun moveTail(currentPosition: Position, target: Position): Position {
        if (abs(target.x - currentPosition.x) < 2 && abs(target.y - currentPosition.y) < 2) {
            return currentPosition
        }
        val x = if (target.x == currentPosition.x) {
            currentPosition.x
        } else if (target.x - currentPosition.x > 0) {
            currentPosition.x + 1
        } else {
            currentPosition.x - 1
        }
        val y = if (target.y == currentPosition.y) {
            currentPosition.y
        } else if (target.y - currentPosition.y > 0) {
            currentPosition.y + 1
        } else {
            currentPosition.y - 1
        }
        return Position(x, y)
    }

    class Movement(val direction: String, var steps: Int)

    class Position(val x: Int, val y: Int) {
        // Unsure why I have to override equals/hashcode at this point.
        override fun equals(other: Any?): Boolean {
            if (other == null || other !is Position) {
                return false
            }
            return x == other.x && y == other.y
        }

        override fun hashCode(): Int {
            var result = x
            result = 31 * result + y
            return result
        }
    }
}