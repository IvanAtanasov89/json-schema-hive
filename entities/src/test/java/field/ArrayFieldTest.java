package field;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class ArrayFieldTest {

    @Test
    void createSqlPart() {
        ArrayField arrayField = ArrayField.builder().name("field_name").type("string").isLast(false).build();
        String actual = arrayField.createSqlPart();
        assertThat(actual, is("field_name array<string>,"));
    }
}
