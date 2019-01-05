package json.schema.hive

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.mockito.Mockito

private val MAPPER = ObjectMapper()

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

    @Nested
    @DisplayName("test the looper")
    inner class SchemaLooperTest {
        @Test
        @DisplayName("fields should be created properly from json schema")
        fun looper() {
            val fileContent = javaClass.getResource("/jsonSchema.json").readText()
            val node = MAPPER.readTree(fileContent)
            val fields = read(node)
            assertThat(fields.createSqlPart(), `is`("firstName string,\nage string,\naliases array<string>"))
        }
    }

    @Nested
    @DisplayName("test the create statement")
    inner class CreateStatementTest {
        @Test
        @DisplayName("Make sure that the created SQL is as expected")
        fun createSql() {
            val fields = Mockito.mock(Fields::class.java)
            Mockito.`when`(fields.createSqlPart()).thenReturn("field1 string,\nfield2 int")
            val actual = CreateStatement(tableName = "click", fields = fields).createSql()
            assertThat(actual, `is`("CREATE TABLE click (\n  field1 string,\n  field2 int\n);"))
        }
    }
}
