package days

import org.junit.jupiter.api.assertThrows
import util.FileReader
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TestDays {
    @Test
    fun testDay01() {
        val day01 = Day01(FileReader.readFile("1.txt").readLines())
        assertEquals("71924", day01.task1())
        assertEquals("210406", day01.task2())
    }

    @Test
    fun testDay02() {
        val two = Day02(FileReader.readFile("2.txt").readLines())
        assertEquals("14069", two.task1())
        assertEquals("12411", two.task2())
    }

    @Test
    fun testDay03() {
        val three = Day03(FileReader.readFile("3.txt").readLines())
        assertEquals("7817", three.task1())
        assertEquals("2444", three.task2())
        val wrongThree = Day03(FileReader.readFile("2.txt").readLines())
        assertThrows<Exception> { wrongThree.task1() }
    }

    @Test
    fun testDay04() {
        val day04 = Day04(FileReader.readFile("4.txt").readLines())
        assertEquals("464", day04.task1())
        assertEquals("770", day04.task2())
    }

    @Test
    fun testDay05() {
        val day05 = Day05(FileReader.readFile("5.txt").readLines())
        assertEquals("LBLVVTVLP", day05.task1())
        assertEquals("TPFFBDRJD", day05.task2())
    }

    @Test
    fun testDay06() {
        val day06 = Day06(FileReader.readFile("6.txt").readLines())
        assertEquals("1142", day06.task1())
        assertEquals("2803", day06.task2())
    }

    @Test
    fun testDay07() {
        val day07 = Day07(FileReader.readFile("7.txt").readLines())
        assertEquals("1077191", day07.task1())
        assertEquals("5649896", day07.task2())
    }

    @Test
    fun testDay08() {
        val day08 = Day08(FileReader.readFile("8.txt").readLines())
        assertEquals("1776", day08.task1())
        assertEquals("234416", day08.task2())
    }

    @Test
    fun testDay09() {
        val day09 = Day09(FileReader.readFile("9.txt").readLines())
        assertEquals("6090", day09.task1())
        assertEquals("2566", day09.task2())
    }

    @Test
    fun testDay10() {
        val day10 = Day10(FileReader.readFile("10.txt").readLines())
        assertEquals("12840", day10.task1())
        assertEquals(
            "########  ##    ##      ####  ########  ######        ####  ########  ########  \n" +
                    "      ##  ##  ##          ##  ##        ##    ##        ##  ##              ##  \n" +
                    "    ##    ####            ##  ######    ######          ##  ######        ##    \n" +
                    "  ##      ##  ##          ##  ##        ##    ##        ##  ##          ##      \n" +
                    "##        ##  ##    ##    ##  ##        ##    ##  ##    ##  ##        ##        \n" +
                    "########  ##    ##    ####    ##        ######      ####    ##        ########  \n", day10.task2()
        )
    }
}