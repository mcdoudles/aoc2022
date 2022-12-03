package days

class Three(lines: List<String>) : Day(lines) {
    override fun one(): String {
        var retVal = 0
        lines.map {
            if (it.length % 2 == 1) {
                throw Exception("Wrong Input.")
            }
            arrayOf(
                it.substring(0, it.length / 2),
                it.substring(it.length / 2, it.length)
            )
        }.forEach {
            if (it[0].length != it[1].length) {
                throw Exception("Wrong Input. Splitting error: " + it[0].length + ", " + it[1].length)
            }
            retVal += checkForCharactersInStrings(it[0], it[1])
        }
        return retVal.toString()
    }


    override fun two(): String {
        var i = 0
        var tripletMap = mapOf<Int, Array<String>>()
        var retVal = 0
        while (i < lines.size) {
            tripletMap = tripletMap.plus(i to arrayOf(lines[i], lines[i + 1], lines[i + 2]))
            i += 3
        }

        for (triplet in tripletMap) {
            retVal += checkForCharactersInStrings(triplet.value[0], triplet.value[1], triplet.value[2])
        }

        return retVal.toString()
    }

    private fun checkForCharactersInStrings(vararg strings: String): Int {
        val listOfChars = strings[0].split("".toRegex()).filter { it != "" }
        var contains: String? = null
        for (a in listOfChars) {
            var i = 1
            while (i < strings.size) {
                if (!strings[i].contains(a)) {
                    contains = null
                    break
                }
                contains = a
                i++
            }
            if (contains != null) {
                break
            }
        }

        if (contains == null) {
            throw Exception("Wrong Input. Probably... Or I did something very wrong.")
        }

        val char = contains.toCharArray()[0]
        return if (char.isUpperCase()) char.code - 38 else char.code - 96
    }
}
