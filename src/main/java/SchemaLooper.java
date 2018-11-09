import com.fasterxml.jackson.databind.JsonNode;

import java.util.Iterator;
import java.util.Map;

public class SchemaLooper {

    public static void readProperties(JsonNode node) {

        Iterator<Map.Entry<String, JsonNode>> nodes = node.get("properties").fields();

        while (nodes.hasNext()) {
            Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();
            writeField(entry.getKey(), entry.getValue());
        }

        // Loop through properties

    }


    private static void writeField(String field, JsonNode attributes) {
        String type = attributes.get("type").asText();

        if (type.equals("object")) {
            System.out.println(field + " struct<");
            readProperties(attributes);
            System.out.println(">");
        } else {
            System.out.println(field + " " + type + ",");
        }
    }

}
