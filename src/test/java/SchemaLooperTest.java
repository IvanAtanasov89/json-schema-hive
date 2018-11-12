import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class SchemaLooperTest {

    @Test
    @DisplayName("Should read the node correctly")
    void build() throws IOException {
        FileInputStream file = new FileInputStream("src/test/resources/jsonSchema.json");
        String json = IOUtils.toString(file, "UTF-8");
        final ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        assertThat(SchemaLooper.readProperties(node).createSqlPart(), is(""));
    }
}
