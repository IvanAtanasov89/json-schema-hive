import org.junit.jupiter.api.Test

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested

internal class TypeTest {

    @Nested
    @DisplayName("Test the String Type")
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
}
