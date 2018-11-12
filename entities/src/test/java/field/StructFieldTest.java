package field;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StructFieldTest {

    private static final String FIELD_REPRESENTATION = "nested_field_name string";

    @Mock Fields fields;

    @Test
    void createSqlPart() {
        when(fields.createSqlPart()).thenReturn(FIELD_REPRESENTATION);
        StructField structField = StructField.builder()
                .name("field_name")
                .fields(fields)
                .isLast(false)
                .build();

        String expected = "field_name struct<\n  nested_field_name string\n>,";
        String actual = structField.createSqlPart();
        assertThat(actual, is(expected));
    }
}
