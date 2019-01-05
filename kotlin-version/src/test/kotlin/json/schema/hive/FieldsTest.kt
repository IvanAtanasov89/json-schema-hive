package json.schema.hive

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

private const val SQL_1 = "field1 string"
private const val SQL_2 = "field2 string"

internal class FieldsTest {

    @Test
    fun createSqlPart() {
        val field1 = mock(Field::class.java)
        val field2 = mock(Field::class.java)
        `when`(field1.createSqlPart()).thenReturn(SQL_1)
        `when`(field2.createSqlPart()).thenReturn(SQL_2)

        val actual = Fields().addField(field1).addField(field2).createSqlPart()

        assertThat(actual, `is`("field1 string\nfield2 string"))
    }
}
