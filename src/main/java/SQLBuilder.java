public class SQLBuilder {

    private String createStatement;
    private boolean newObject = true;
    private static final int INDENT_SPACES = 2;

    public SQLBuilder(final String tableName) {
        createStatement = String.format("CREATE TABLE %s (", tableName);
    }

    public SQLBuilder addField(String name, String type) {
        createStatement = String.format("%s\n%s %s,", createStatement, name, type);
        return this;
    }

    public String build() {
        removeTrailingComma();
        return createStatement + "\n)";
    }

    private void removeTrailingComma() {
        createStatement = createStatement.substring(0, createStatement.length() - 1);
    }

}
