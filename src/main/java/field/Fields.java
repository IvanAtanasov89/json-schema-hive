package field;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fields implements SqlString {

    private final List<SqlString> fields = new ArrayList<>();

    public void addField(SqlString field) {
        fields.add(field);
    }

    @Override
    public String getSqlString() {
        List<String> sqlStrings = fields.stream().map(SqlString::getSqlString).collect(Collectors.toList());
        return String.join("\n", sqlStrings);
    }

}
