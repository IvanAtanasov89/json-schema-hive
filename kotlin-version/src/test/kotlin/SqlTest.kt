import org.junit.jupiter.api.Test

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.DisplayName

internal class SqlTest {

    @Test
    @DisplayName("indent works with multiple lines")
    fun indent() {
        val formatted = SqlFormatter("yo\nyoo").indent().format()
        assertThat(formatted, `is`("  yo\n  yoo"))
    }

    @Test
    @DisplayName("nothing appended when is last")
    fun addEndingWhenIsLast() {
        val formatted = SqlFormatter("mooo").addEnding(true).format()
        assertThat(formatted, `is`("mooo"))
    }

    @Test
    @DisplayName("string is prefixed and suffixed with a new line character")
    fun addNewLines() {
        val formatted = SqlFormatter("mooo").addNewLines().format()
        assertThat(formatted, `is`("\nmooo\n"))
    }

    @Test
    @DisplayName("comma appended when not last")
    fun addEndingWhenNotLast() {
        val formatted = SqlFormatter("mooo").addEnding(false).format()
        assertThat(formatted, `is`("mooo,"))
    }
}
