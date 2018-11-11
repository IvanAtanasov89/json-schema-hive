import com.fasterxml.jackson.databind.JsonNode;
import field.BasicField;
import field.Fields;
import field.StructField;

import java.util.Iterator;
import java.util.Map;

public class SchemaLooper {

    public static Fields readProperties(JsonNode node, int level) {

        final Fields fields = new Fields();
        final Iterator<Map.Entry<String, JsonNode>> nodes = node.get("properties").fields();

        while (nodes.hasNext()) {
            Map.Entry<String, JsonNode> entry = nodes.next();
            String fieldName = entry.getKey();
            String type = entry.getValue().get("type").asText();
            boolean isLast = isLastField(nodes);
            int nextLevel = level + 1;

            if (type.equals("object")) {
                fields.addField(
                        StructField.builder()
                                .name(fieldName)
                                .fields(readProperties(entry.getValue(), nextLevel))
                                .level(nextLevel)
                                .isLast(isLast)
                                .build()
                );
            } else {
               fields.addField(
                       BasicField.builder()
                               .name(fieldName)
                               .type(type)
                               .level(level)
                               .isLast(isLast)
                               .build()
               );
            }
        }

        return fields;

    }

    private static boolean isLastField(Iterator fields) {
        return !fields.hasNext();
    }

}
