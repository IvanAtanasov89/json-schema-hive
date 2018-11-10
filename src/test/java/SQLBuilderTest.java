import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SQLBuilderTest {

    @Test
    void build() {
        final String expected = "CREATE TABLE table_name (\n" +
                "field1 string,\n" +
                "field2 string\n" +
                ")";

        final String actual = new SQLBuilder("table_name")
                .addField("field1", "string")
                .addField("field2", "string")
                .build();

        assertEquals(expected, actual);
    }
}
