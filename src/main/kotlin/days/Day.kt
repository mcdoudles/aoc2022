package days

abstract class Day(val lines: List<String>) {
    abstract fun one(): String
    abstract fun two(): String
}