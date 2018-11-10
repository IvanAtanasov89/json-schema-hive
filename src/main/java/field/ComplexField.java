package field;

public class ComplexField implements SqlString {

    private final String name;
    private final Fields fields;
    private final int level;

    public ComplexField(final String name, final Fields fields, final int level) {
        this.name = name;
        this.fields = fields;
        this.level = level;
    }

    @Override
    public String getSqlString() {
        return String.format("%s struct<\n%s\n>", name, fields.getSqlString());
    }
}
