import days.*
import util.FileReader
import java.io.FileNotFoundException

const val seperator = "---------------------------------------"

fun main() {
    var dayNumber = 1
    while (dayNumber < 25) {
        val day = getDay(dayNumber)
        if (day != null) {
            getResultsForADay(dayNumber, day::one, day::two)
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
    return mapOf(1 to One(lines), 2 to Two(lines), 3 to Three(lines), 4 to Four(lines), 5 to Five(lines))
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