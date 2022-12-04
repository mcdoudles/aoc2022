package days

import org.junit.jupiter.api.assertThrows
import util.FileReader
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TestDays {
    @Test
    fun testDayOne() {
        val one = One(FileReader.readFile("1.txt").readLines())
        assertEquals("71924", one.one())
        assertEquals("210406", one.two())
    }

    @Test
    fun testDayTwo() {
        val two = Two(FileReader.readFile("2.txt").readLines())
        assertEquals("14069", two.one())
        assertEquals("12411", two.two())
    }

    @Test
    fun testDayThree() {
        val three = Three(FileReader.readFile("3.txt").readLines())
        assertEquals("7817", three.one())
        assertEquals("2444", three.two())
        val wrongThree = Three(FileReader.readFile("2.txt").readLines())
        assertThrows<Exception> { wrongThree.one() }
    }

    @Test
    fun testDayFour() {
        val four = Four(FileReader.readFile("4.txt").readLines())
        assertEquals("464", four.one())
        assertEquals("770", four.two())
    }
}