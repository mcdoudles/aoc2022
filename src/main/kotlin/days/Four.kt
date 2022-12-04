package days

class Four(lines: List<String>) : Day(lines) {
    private val firstComparedToThird = "0c2"
    private val firstComparedToFourth = "0c3"
    private val secondComparedToThird = "1c2"
    private val secondComparedToFourth = "1c3"

    override fun one(): String {
        var result = 0
        for (conv in mapLinesToComparison()) {
            if (conv[firstComparedToThird]!! >= 0 && conv[secondComparedToFourth]!! <= 0) {
                result++
                continue
            }

            if (conv[firstComparedToThird]!! <= 0 && conv[secondComparedToFourth]!! >= 0) {
                result++
            }
        }

        return result.toString()
    }

    override fun two(): String {
        var result = 0
        for (compMap in mapLinesToComparison()) {
            if (compMap[firstComparedToThird]!! >= 0 && compMap[firstComparedToFourth]!! <= 0) {
                result++
                continue
            }

            if (compMap[secondComparedToThird]!! >= 0 && compMap[secondComparedToFourth]!! <= 0) {
                result++
                continue
            }

            if (compMap[firstComparedToThird]!! <= 0 && compMap[secondComparedToThird]!! >= 0) {
                result++
                continue
            }

            if (compMap[firstComparedToFourth]!! <= 0 && compMap[secondComparedToFourth]!! >= 0) {
                result++
            }
        }

        return result.toString()
    }

    private fun mapLinesToComparison(): List<Map<String, Int>> {
        return lines.map {
            val num0 = Integer.parseInt(it.substring(0, it.indexOf('-')))
            val num1 = Integer.parseInt(it.substring(it.indexOf('-') + 1, it.indexOf(',')))
            val num2 = Integer.parseInt(it.substring(it.indexOf(',') + 1, it.lastIndexOf('-')))
            val num3 = Integer.parseInt(it.substring(it.lastIndexOf('-') + 1, it.length))
            mapOf(
                firstComparedToThird to num0.compareTo(num2),
                firstComparedToFourth to num0.compareTo(num3),
                secondComparedToThird to num1.compareTo(num2),
                secondComparedToFourth to num1.compareTo(num3)
            )
        }
    }
}