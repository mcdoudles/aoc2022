package days

import org.junit.jupiter.api.assertThrows
import util.FileReader
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TestDays {
    @Test
    fun testDayOne() {
        val day01 = Day01(FileReader.readFile("1.txt").readLines())
        assertEquals("71924", day01.task1())
        assertEquals("210406", day01.task2())
    }

    @Test
    fun testDayTwo() {
        val two = Day02(FileReader.readFile("2.txt").readLines())
        assertEquals("14069", two.task1())
        assertEquals("12411", two.task2())
    }

    @Test
    fun testDayThree() {
        val three = Day03(FileReader.readFile("3.txt").readLines())
        assertEquals("7817", three.task1())
        assertEquals("2444", three.task2())
        val wrongThree = Day03(FileReader.readFile("2.txt").readLines())
        assertThrows<Exception> { wrongThree.task1() }
    }

    @Test
    fun testDayFour() {
        val day04 = Day04(FileReader.readFile("4.txt").readLines())
        assertEquals("464", day04.task1())
        assertEquals("770", day04.task2())
    }

    @Test
    fun testDayFive() {
        val day05 = Day05(FileReader.readFile("5.txt").readLines())
        assertEquals("LBLVVTVLP", day05.task1())
        assertEquals("TPFFBDRJD", day05.task2())
    }

    @Test
    fun testDaySix() {
        val day06 = Day06(FileReader.readFile("6.txt").readLines())
        assertEquals("1142", day06.task1())
        assertEquals("2803", day06.task2())
    }

    @Test
    fun testDaySeven() {
        val day07 = Day07(FileReader.readFile("7.txt").readLines())
        assertEquals("1077191", day07.task1())
        assertEquals("5649896", day07.task2())
    }
}