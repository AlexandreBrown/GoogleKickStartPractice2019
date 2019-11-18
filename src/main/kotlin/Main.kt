
private const val WRONG_ANSWER = "WRONG_ANSWER"

fun main() {
    val numberOfTestCases = getNumberOfTestCases()

    testCases@ for (testCase in 0 until numberOfTestCases) {

        var inclusiveGuessingRange = getInclusiveGuessingRange()
        val numberOfTriesAllowed = getNumberOfRetriesAllowed()

        var attempts = 0

        while (attempts < numberOfTriesAllowed) {

            val guessedNumber = guessNumber(inclusiveGuessingRange)

            val response = readLine() ?: WRONG_ANSWER

            when {
                isCorrect(response) -> continue@testCases
                isTooBig(response) -> inclusiveGuessingRange = computeLowerRange(inclusiveGuessingRange, guessedNumber)
                isTooSmall(response) -> inclusiveGuessingRange =
                    computeHigherRange(inclusiveGuessingRange, guessedNumber)
                isWrongAnswer(response) -> continue@testCases
            }

            attempts++
        }
    }
}

fun output(text: String) {
    println(text)
    System.out.flush()
}

fun getNumberOfTestCases(): Int = readLine()?.toInt() ?: 0

fun getInclusiveGuessingRange(): IntRange {

    val values: List<String> = readLine()?.split(" ") ?: listOf()

    val firstValue = values.first().toInt() + 1
    val secondValue = values.last().toInt()

    return firstValue..secondValue
}

fun getNumberOfRetriesAllowed(): Int = readLine()?.toInt() ?: 0

fun guessNumber(guessingRange: IntRange): Int {

    val guess = (guessingRange.last + guessingRange.first) / 2

    output("$guess")

    return guess
}

fun isCorrect(value: String): Boolean = value == "CORRECT"

fun isTooSmall(value: String): Boolean = value == "TOO_SMALL"

fun isTooBig(value: String): Boolean = value == "TOO_BIG"

fun isWrongAnswer(value: String): Boolean = value == WRONG_ANSWER

fun computeHigherRange(initialRange: IntRange, guessedNumber: Int): IntRange = (guessedNumber + 1)..initialRange.last

fun computeLowerRange(initialRange: IntRange, guessedNumber: Int): IntRange = initialRange.first until guessedNumber