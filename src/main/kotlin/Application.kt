import days.*
import util.FileReader
import java.io.FileNotFoundException

const val seperator = "---------------------------------------"

fun main() {
    var dayNumber = 1
    while (dayNumber < 25) {
        val day = getDay(dayNumber)
        if (day != null) {
            getResultsForADay(dayNumber, day::task1, day::task2)
        }
        dayNumber++
    }
}

fun getResultsForADay(day: Int, taskOne: () -> String, taskTwo: () -> String) {
    println()
    printWithDashes("DAY $day")

    printWithDashes(taskOne())
    printWithDashes(taskTwo())

    println(seperator)
}

fun createDayMap(lines: List<String>): Map<Int, Day> {
    return mapOf(
        1 to Day01(lines),
        2 to Day02(lines),
        3 to Day03(lines),
        4 to Day04(lines),
        5 to Day05(lines),
        6 to Day06(lines),
        7 to Day07(lines),
        8 to Day08(lines),
        9 to Day09(lines),
        10 to Day10(lines)
    )
}

fun getDay(dayNumber: Int): Day? {
    return try {
        val lines = FileReader.readFile("$dayNumber.txt").readLines()
        val map = createDayMap(lines)
        map[dayNumber]
    } catch (exception: FileNotFoundException) {
        null
    }
}

fun printWithDashes(result: String) {
    var dashLength = (seperator.length - result.length - 1) / 2
    while (dashLength > 0) {
        print("-")
        dashLength--
    }
    print(" $result ")
    dashLength = (seperator.length - result.length - 1)
    if (dashLength % 2 == 0) {
        dashLength--
    }
    dashLength /= 2

    while (dashLength > 0) {
        print("-")
        dashLength--
    }
    println()
}