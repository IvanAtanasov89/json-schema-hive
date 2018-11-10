package field;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComplexFieldTest {

    @Mock private Fields fields;

    @Test
    @DisplayName("Wraps the fields with the field name ans struct")
    void getSqlString() {
        String expected = "fieldName struct<\n  field string\n>";
        when(fields.getSqlString()).thenReturn("  field string");
        ComplexField complexField = new ComplexField("fieldName", fields, 0);
        assertThat(complexField.getSqlString(), is(expected));
    }
}
