import com.fasterxml.jackson.databind.JsonNode;
import field.BasicField;
import field.Field;
import field.Fields;
import type.Array;
import type.StringType;
import type.Struct;
import type.Type;

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
        boolean isLast = isLastField(nodes);
        return BasicField.builder().name(fieldName).type(readType(entry.getValue())).isLast(isLast).build();

    }

    private static Type readType(JsonNode node) {
        String type = node.get("type").asText();
        switch (type) {
            case "object":
                return Struct.builder().fields(readProperties(node)).build();
            case "array":
                return Array.builder().type(readType(node.get("items"))).build();
            default:
                return new StringType();
        }
    }

    private static boolean isLastField(Iterator fields) {
        return !fields.hasNext();
    }
}
