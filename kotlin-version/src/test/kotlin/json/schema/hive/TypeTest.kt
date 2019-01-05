package json.schema.hive

import org.junit.jupiter.api.Test

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.mockito.Mockito
import kotlin.properties.Delegates

class TypeTest {
    @Nested
    @DisplayName("Test the String json.schema.hive.Type")
    inner class StringTypeTest {
        @Test
        fun createSqlPart() {
            assertThat(StringType().createSqlPart(), `is`("string"))
        }

        @Test
        fun basic() {
            assertThat(StringType().basic(), `is`(true))
        }
    }

    @Nested
    @DisplayName("Test the json.schema.hive.Struct json.schema.hive.Type")
    inner class StructTest {

        private var struct : Struct by Delegates.notNull()

        @BeforeEach
        fun setUp() {
            val fields = Mockito.mock(Fields::class.java)
            Mockito.`when`(fields.createSqlPart()).thenReturn("field1 string,\nfield2 int")
            struct = Struct(fields)
        }

        @Test
        fun createSqlPart() {
            assertThat(struct.createSqlPart(), `is`("struct<\n  field1 string,\n  field2 int\n>"))
        }

        @Test
        fun basic() {
            assertThat(struct.basic(), `is`(false))
        }
    }
}
