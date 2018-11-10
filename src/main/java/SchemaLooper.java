import com.fasterxml.jackson.databind.JsonNode;
import field.ComplexField;
import field.Fields;
import field.SimpleField;

import java.util.Iterator;
import java.util.Map;

public class SchemaLooper {

    public static Fields readProperties(JsonNode node, int level) {

        final Fields fields = new Fields();
        final Iterator<Map.Entry<String, JsonNode>> nodes = node.get("properties").fields();

        while (nodes.hasNext()) {
            Map.Entry<String, JsonNode> entry = nodes.next();
            String field = entry.getKey();
            String type = entry.getValue().get("type").asText();
            if (type.equals("object")) {
                fields.addField(new ComplexField(field, readProperties(entry.getValue(),level + 1), level + 1));
            } else {
               fields.addField(new SimpleField(field, type, level, isLastField(nodes)));
            }
        }

        return fields;

    }

    private static boolean isLastField(Iterator fields) {
        return !fields.hasNext();
    }


//    private static void processFields(String field, JsonNode attributes) {
//        String type = attributes.get("type").asText();
//
//        if (type.equals("object")) {
//            readProperties(attributes);
//        } else {
//            System.out.println(field + " " + type + ",");
//        }
//    }
//
//
//    private static void writeField(String field, JsonNode attributes) {
//        String type = attributes.get("type").asText();
//
//        if (type.equals("object")) {
//            System.out.println(field + " struct<");
//            readProperties(attributes);
//            System.out.println(">");
//        } else {
//            System.out.println(field + " " + type + ",");
//        }
//    }

}
