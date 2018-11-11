package field;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fields implements Field {

    private final List<Field> fields = new ArrayList<>();

    public void addField(Field field) {
        fields.add(field);
    }

    @Override
    public String createSqlPart() {
        List<String> sqlStrings = fields.stream().map(Field::createSqlPart).collect(Collectors.toList());
        return String.join("\n", sqlStrings);
    }
}
