import field.Field;
import field.Fields;

public class CreateStatement implements SqlString {

    private final String tableName;
    private final Fields fields;

    public CreateStatement(final String tableName) {
        this.tableName = tableName;
        this.fields = new Fields();
    }

    public CreateStatement addField(Field field) {
        fields.addField(field);
        return this;
    }

    @Override
    public String getSqlString() {
        return String.format("CREATE TABLE %s (\n%s\n);", tableName, fields);
    }
}
