import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.lang.Exception
import kotlin.random.Random

internal class MainKtTest {

    private lateinit var output: ByteArrayOutputStream

    @Before
    fun setup() {
        output = ByteArrayOutputStream()
        System.setOut(PrintStream(output))
    }

    @Test
    fun `returns number of test cases from standard input`() {

        val expectedValue = 2
        input("$expectedValue")

        val numberOfTestCases = getNumberOfTestCases()

        assertEquals(expectedValue, numberOfTestCases)
    }

    @Test
    fun `returns the inclusive guessing range from standard input`() {

        val exclusiveGuessingRange = 0..Random.nextInt(3, 6)
        val expectedGuessingRange = (exclusiveGuessingRange.first + 1)..exclusiveGuessingRange.last
        input("${exclusiveGuessingRange.first} ${exclusiveGuessingRange.last}")

        val guessingRange = getInclusiveGuessingRange()

        assertEquals(expectedGuessingRange, guessingRange)
    }

    @Test(expected = Exception::class)
    fun `throws when getting guessing range with invalid first number`() {

        input("invalid ${Random.nextInt()}")

        getInclusiveGuessingRange()
    }

    @Test(expected = Exception::class)
    fun `throws when getting guessing range with invalid second number`() {

        input("${Random.nextInt()} invalid")

        getInclusiveGuessingRange()
    }

    @Test
    fun `returns number of retries allowed from standard input`() {

        val expectedNumberRetriesAllowed = Random.nextInt()
        input("$expectedNumberRetriesAllowed")

        val numberRetriesAllowed = getNumberOfRetriesAllowed()

        assertEquals(expectedNumberRetriesAllowed, numberRetriesAllowed)
    }

    @Test
    fun `guesses the middle number of the guessing range to standard output`() {

        val guessingRange = 5..15
        val expectedGuess = (guessingRange.last + guessingRange.first) / 2

        val guessedNumber = guessNumber(guessingRange)

        assertEquals(expectedGuess, guessedNumber)
    }

    @Test
    fun `isTooSmall returns true when value is TOO_SMALL`() {

        val input = "TOO_SMALL"

        assertTrue(isTooSmall(input))
    }

    @Test
    fun `isTooSmall returns false when value is not TOO_SMALL`() {

        val input = "TOO_SMALL1"

        assertFalse(isTooSmall(input))
    }

    @Test
    fun `isTooBig returns true when value is TOO_BIG`() {

        val input = "TOO_BIG"

        assertTrue(isTooBig(input))
    }

    @Test
    fun `isTooBig returns false when value is not TOO_BIG`() {

        val input = "TOO_BIG1"

        assertFalse(isTooBig(input))
    }

    @Test
    fun `isWrongAnswer returns true when value is WRONG_ANSWER`() {

        val input = "WRONG_ANSWER"

        assertTrue(isWrongAnswer(input))
    }

    @Test
    fun `isWrongAnswer returns false when value is not WRONG_ANSWER`() {

        val input = "WRONG_ANSWER1"

        assertFalse(isWrongAnswer(input))
    }

    @Test
    fun `isCorrect returns true when value is CORRECT`() {

        val input = "CORRECT"

        assertTrue(isCorrect(input))
    }

    @Test
    fun `isCorrect returns false when value is not CORRECT`() {

        val input = "CORRECT!"

        assertFalse(isCorrect(input))
    }

    @Test
    fun `computes range with lower bound bigger than guessed number and higher bound equal to initial range higher bound`() {

        val initialInclusiveRange = 3..6
        val guessedNumber = 4
        val expectedHigherRange = (guessedNumber + 1)..initialInclusiveRange.last()

        val higherRange = computeHigherRange(initialInclusiveRange, guessedNumber)

        assertEquals(expectedHigherRange, higherRange)
    }

    @Test
    fun `computes range with higher bound lower than guessed number and lower bound equal to initial range lower bound`() {

        val initialInclusiveRange = 1..10
        val guessedNumber = 4
        val expectedLowerRange = initialInclusiveRange.first() until guessedNumber

        val lowerRange = computeLowerRange(initialInclusiveRange, guessedNumber)

        assertEquals(expectedLowerRange, lowerRange)
    }

    private fun input(text: String) {
        System.setIn(ByteArrayInputStream(text.toByteArray()))
    }
}