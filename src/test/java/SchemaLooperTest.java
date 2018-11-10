import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class SchemaLooperTest {

    @Test
    @DisplayName("Should read the node correctly")
    void build() throws IOException {


        String json = "{\n" +
                "  \"$id\": \"https://example.com/person.schema.json\",\n" +
                "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
                "  \"title\": \"Person\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "    \"firstName\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"description\": \"The person's first name.\"\n" +
                "    },\n" +
                "    \"lastName\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"description\": \"The person's last name.\"\n" +
                "    },\n" +
                "    \"age\": {\n" +
                "      \"description\": \"Age in years which must be equal to or greater than zero.\",\n" +
                "      \"type\": \"integer\",\n" +
                "      \"minimum\": 0\n" +
                "    },\n" +
                "    \"nested\": {\n" +
                "        \"type\": \"object\",\n" +
                "        \"properties\": {\n" +
                "            \"hello\": {\n" +
                "                \"type\": \"string\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        final ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);

        assertThat(SchemaLooper.readProperties(node, 0).getSqlString(), is(""));


    }
}
