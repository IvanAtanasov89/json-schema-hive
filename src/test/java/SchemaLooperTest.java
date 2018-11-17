import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import field.BasicField;
import field.Fields;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import type.Array;
import type.StringType;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class SchemaLooperTest {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Test
    @DisplayName("Should read the node correctly")
    void build() throws IOException {
        String expected = TestHelper.readResource("src/test/resources/expectedSql.txt").trim();
        JsonNode node = MAPPER.readTree(TestHelper.readResource("src/test/resources/jsonSchema.json"));

        assertThat(SchemaLooper.readProperties(node), is(createExpected()));
    }

    private static Fields createExpected() {
        Fields fields = new Fields();
        fields.addField(BasicField.builder().name("firstName").type(new StringType()).isLast(false).build());
        fields.addField(BasicField.builder().name("age").type(new StringType()).isLast(false).build());
        fields.addField(
                BasicField.builder()
                        .name("aliases")
                        .type(Array.builder().type(new StringType()).build())
                        .isLast(true)
                        .build()
        );
        return fields;
    }
}
