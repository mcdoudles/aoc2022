package days

class Day02(lines: List<String>) : Day(lines) {
    override fun task1(): String {
        var result = 0L
        val matchUps = mapOf(
            // ROCK = 1, PAPER = 2, SCI = 3
            "A X" to 4L, // ROCK ROCK = DRAW 3 + 1
            "A Y" to 8L, // ROCK PAPER = WIN 6 + 2
            "A Z" to 3L, // ROCK SCI = LOSE 0 + 3
            "B X" to 1L, // PAPER ROCK = LOSE 0 + 1
            "B Y" to 5L, // PAPER PAPER = DRAW 3 + 2
            "B Z" to 9L, // PAPER SCI = WIN 6 + 3
            "C X" to 7L, // SCI ROCK = WIN 6 + 1
            "C Y" to 2L, // SCI PAPER = LOSE 0 + 2
            "C Z" to 6L  // SCI SCI = DRAW 3 + 3
        )
        for (singleMatch in matchUps) {
            result += calculateValueOfCombination(singleMatch.key, singleMatch.value)
        }
        return result.toString()
    }

    override fun task2(): String {

        val matchUps = mapOf(
            // ROCK = 1, PAPER = 2, SCI = 3
            "A X" to 3L, // ROCK LOSE = SCI 0 + 3
            "A Y" to 4L, // ROCK DRAW = ROCK 3 + 1
            "A Z" to 8L, // ROCK WIN = PAPER 6 + 2
            "B X" to 1L, // PAPER LOSE = ROCK 0 + 1
            "B Y" to 5L, // PAPER DRAW = PAPER 3 + 2
            "B Z" to 9L, // PAPER WIN = SCI 6 + 3
            "C X" to 2L, // SCI LOSE = PAPER 0 + 2
            "C Y" to 6L, // SCI DRAW = SCI 3 + 3
            "C Z" to 7L  // SCI WIN = ROCK 6 + 1
        )

        return getResultForMatchUps(matchUps).toString()
    }

    private fun getResultForMatchUps(matchUps: Map<String, Long>): Long {
        var result = 0L
        for (singleMatch in matchUps) {
            result += calculateValueOfCombination(singleMatch.key, singleMatch.value)
        }
        return result
    }

    private fun calculateValueOfCombination(
        matchUp: String,
        value: Long
    ): Long {
        return lines.stream().filter {
            it.equals(matchUp)
        }.count() * value
    }
}