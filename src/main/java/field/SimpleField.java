package field;

import format.Indenter;

public class SimpleField implements SqlString {

    private final String name;
    private final String type;
    private final int level;
    private final boolean isLast;

    public SimpleField(final String name, final String type, int level, boolean isLast) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.isLast = isLast;
    }

    @Override
    public String getSqlString() {
        return Indenter.indent(level, String.format("%s %s%s", name, type, commaOrNoComma()));
    }

    private String commaOrNoComma() {
        return isLast ? "" : ",";
    }
}
