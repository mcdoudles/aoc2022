package days

abstract class Day(val lines: List<String>) {
    abstract fun task1(): String
    abstract fun task2(): String
}