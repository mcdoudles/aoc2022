package days

class Four(lines: List<String>) : Day(lines) {
    override fun one(): String {
        var result = 0
        for (values in mapLinesToValues()) {
            // first range is within the second range
            if (values[0] in values[2]..values[3] && values[1] in values[2]..values[3]) {
                result++
                continue
            }

            // second range is within the first range
            if (values[2] in values[0]..values[1] && values[3] in values[0]..values[1]) {
                result++
            }
        }

        return result.toString()
    }

    override fun two(): String {
        var result = 0
        for (values in mapLinesToValues()) {

            // first number is within the second range
            if (values[0] in values[2]..values[3]) {
                result++
                continue
            }

            // second number is within the second range
            if (values[1] in values[2]..values[3]) {
                result++
                continue
            }

            // third number is within the first range
            if (values[2] in values[0]..values[1]) {
                result++
                continue
            }

            // fourth number is within the first range
            if (values[3] in values[0]..values[1]) {
                result++
                continue
            }
        }

        return result.toString()
    }

    private fun mapLinesToValues(): List<Array<Int>> {
        return lines.map {
            arrayOf(
                Integer.parseInt(it.substring(0, it.indexOf('-'))),
                Integer.parseInt(it.substring(it.indexOf('-') + 1, it.indexOf(','))),
                Integer.parseInt(it.substring(it.indexOf(',') + 1, it.lastIndexOf('-'))),
                Integer.parseInt(it.substring(it.lastIndexOf('-') + 1, it.length))
            )
        }
    }
}