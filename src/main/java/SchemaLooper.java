import com.fasterxml.jackson.databind.JsonNode;
import field.ArrayField;
import field.BasicField;
import field.Field;
import field.Fields;
import field.StructField;

import java.util.Iterator;
import java.util.Map;

public class SchemaLooper {

    public static Fields readProperties(JsonNode node) {
        final Fields fields = new Fields();
        final Iterator<Map.Entry<String, JsonNode>> nodes = node.get("properties").fields();

        while (nodes.hasNext()) {
            fields.addField(createField(nodes));
        }
        return fields;

    }

    private static Field createField(Iterator<Map.Entry<String, JsonNode>> nodes) {
        Map.Entry<String, JsonNode> entry = nodes.next();
        String fieldName = entry.getKey();
        String type = entry.getValue().get("type").asText();
        boolean isLast = isLastField(nodes);

        switch (type) {
            case "object":
                return StructField.builder().name(fieldName).fields(readProperties(entry.getValue())).isLast(isLast).build();
            case "array":
                String arrayType = entry.getValue().get("items").get("type").asText();
                return ArrayField.builder().name(fieldName).type(arrayType).isLast(isLast).build();
            default:
                return BasicField.builder().name(fieldName).type(type).isLast(isLast).build();
        }
    }

    private static boolean isLastField(Iterator fields) {
        return !fields.hasNext();
    }
}
