package json.schema.hive

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class BasicFieldTest {

    @Test
    @DisplayName("tests the creation of SQL string")
    fun createSqlPart() {
        val basicField = BasicField(name = "hahaha", type = StringType(), isLast = false)
        val actual = basicField.createSqlPart()
        assertThat(actual, `is`("hahaha string,"))
    }
}
