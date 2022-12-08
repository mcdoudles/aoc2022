package days

typealias Grid = List<List<Int>>

/**
 * @see <a href="https://adventofcode.com/2022/day/8">Advent of Code Day 8</a>
 */
class Day08(lines: List<String>) : Day(lines) {

    override fun task1(): String {
        val treeRows: Grid = lines.map { treeLine -> treeLine.map { tree -> tree.digitToInt() } }
        val treeColumns: Grid = List(lines.size) { m -> treeRows.map { it[m] } }

        val gridOfVisibleTrees: List<MutableList<Boolean>> = List(lines.size) { MutableList(lines.size) { false } }
        for (i in treeRows.indices) {
            for (j in treeColumns.indices) {
                gridOfVisibleTrees[i][j] = treeRows[i].isTreeVisible(j) || treeColumns[j].isTreeVisible(i)
            }
        }

        return gridOfVisibleTrees.sumOf { visibleList -> visibleList.count { visible -> visible } }.toString()
    }

    override fun task2(): String {
        val treeRows: Grid = lines.map { treeLine -> treeLine.map { tree -> tree.digitToInt() } }
        val treeColumns: Grid = List(lines.size) { m -> treeRows.map { it[m] } }
        return getScoreForTree(treeRows, treeColumns).toString()
    }

    private fun List<Int>.isTreeVisible(i: Int) =
        drop(i + 1).none { it >= get(i) } || reversed().drop(size - i).none { it >= get(i) }

    private fun getScoreForTree(treeRows: Grid, treeColumns: Grid): Int {
        var result = -1
        for (i in treeRows.indices) {
            for (j in treeColumns.indices) {
                // Get Score in every direction and multiply them.
                val east = treeRows[i].getScoreForSingleLine(j)
                val west = treeRows[i].reversed().getScoreForSingleLine(treeRows[i].size - 1 - j)
                val south = treeColumns[j].getScoreForSingleLine(i)
                val north = treeColumns[j].reversed().getScoreForSingleLine(treeColumns[j].size - 1 - i)
                result = maxOf(result, north * east * south * west)
            }
        }
        return result
    }

    private fun List<Int>.getScoreForSingleLine(treeIndex: Int): Int {
        val rest = drop(treeIndex + 1)
        return if (rest.none { it >= get(treeIndex) }) {
            // if none of them are same size or bigger, just count them
            rest.count()
        } else {
            // you can see all trees + the one that is blocking
            1 + rest.takeWhile { it < get(treeIndex) }.count()
        }
    }
}