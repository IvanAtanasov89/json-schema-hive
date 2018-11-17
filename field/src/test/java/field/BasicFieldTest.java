package field;

import org.junit.jupiter.api.Test;
import type.StringType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class BasicFieldTest {

    @Test
    void createSqlPart() {
        BasicField basicField = BasicField.builder()
                .name("field_name")
                .type(new StringType())
                .isLast(true)
                .build();

        String expected = "field_name string";
        String actual = basicField.createSqlPart();
        assertThat(actual, is(expected));
    }
}
