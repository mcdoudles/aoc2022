package days

import java.util.*

/**
 * @see <a href="https://adventofcode.com/2022/day/12">Advent of Code Day 12</a>
 */
class Day12(lines: List<String>) : Day(lines) {

    private lateinit var endPoint: Point

    override fun task1(): String {
        val points = createPoints()
        val startPoint = points.findPointWithElevation('S')!!
        endPoint = points.findPointWithElevation('z')!!
        return findShortestPath(startPoint, points).toString()
    }

    override fun task2(): String {
        val points = createPoints()
        val startPoints = points.filter { it.elevation == 'a' || it.elevation == 'S' }
        endPoint = points.findPointWithElevation('z')!!
        val distanceList = mutableListOf<Int>()

        for (startPoint in startPoints) {
            distanceList += findShortestPath(startPoint, points)
        }
        return distanceList.filter { it != -1 }.min().toString()
    }

    private fun createPoints(): List<Point> {
        val points = mutableListOf<Point>()
        lines.forEachIndexed { i, line ->
            line.toCharArray().forEachIndexed { j, char ->
                points += Point(i, j, char)
            }
        }
        return points
    }

    private fun findShortestPath(startPoint: Point, points: List<Point>): Int {
        val queue: Queue<Pair<Point, Int>> = LinkedList()
        queue.add(Pair(startPoint, 0))

        val visited = hashSetOf<Point>()
        visited.add(startPoint)

        while (queue.isNotEmpty()) {
            val (point, distance) = queue.remove()
            if (point == endPoint) return distance - 1

            var currentElevation = point.elevation
            if (currentElevation == 'S') currentElevation = 'a'

            nextPoint(
                points.findPointByCoordinates(point.x - 1, point.y),
                currentElevation,
                visited,
                queue,
                distance
            )
            nextPoint(
                points.findPointByCoordinates(point.x + 1, point.y),
                currentElevation,
                visited,
                queue,
                distance
            )
            nextPoint(
                points.findPointByCoordinates(point.x, point.y - 1),
                currentElevation,
                visited,
                queue,
                distance
            )
            nextPoint(
                points.findPointByCoordinates(point.x, point.y + 1),
                currentElevation,
                visited,
                queue,
                distance
            )
        }
        return -1
    }

    private fun nextPoint(
        currentPosition: Point?,
        currentElevation: Char,
        visited: HashSet<Point>,
        queue: Queue<Pair<Point, Int>>,
        distance: Int
    ) {
        if (currentPosition == null) {
            return
        }

        var nextElevation = currentPosition.elevation
        if (nextElevation == 'E') nextElevation = 'z'
        if (
            currentElevation + 1 == nextElevation ||
            currentElevation >= nextElevation
        ) {
            if (!visited.contains(currentPosition)) {
                visited.add(currentPosition)
                queue.add(Pair(currentPosition, distance + 1))
            }
        }
    }

    class Point(
        val x: Int,
        val y: Int,
        val elevation: Char
    ) {
        override fun toString(): String {
            return "$elevation: ($x, $y)"
        }
    }

    companion object {
        fun List<Point>.findPointByCoordinates(x: Int, y: Int): Point? {
            return find { point -> point.x == x && point.y == y }
        }

        fun List<Point>.findPointWithElevation(elevation: Char): Point? {
            return find { point -> point.elevation == elevation }
        }
    }
}