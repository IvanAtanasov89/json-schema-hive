import field.SimpleField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleFieldTest {
    @Test
    void getSqlString() {
        SimpleField simpleField = new SimpleField("field", "integer", 0, true);
        String actual = simpleField.getSqlString();
        assertEquals(actual, "field integer");
    }
}
