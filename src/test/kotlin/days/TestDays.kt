package days

import util.FileReader
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TestDays {
    @Test
    fun testDayOne() {
        val one = One(FileReader.readFile("1.txt").readLines())
        assertEquals(one.one(), "71924")
        assertEquals(one.two(), "210406")
    }

    @Test
    fun testDayTwo() {
        val two = Two(FileReader.readFile("2.txt").readLines())
        assertEquals(two.one(), "14069")
        assertEquals(two.two(), "12411")
    }
}