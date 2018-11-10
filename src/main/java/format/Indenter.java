package format;

public class Indenter {

    private static final int INDENT_SPACES = 2;

    public static String indent(int level, String text) {
        int indentBy = INDENT_SPACES * level;

        StringBuilder sb = new StringBuilder(indentBy);
        for (int i = 0; i < indentBy; i++) {
            sb.append(" ");
        }
        return sb.toString() + text;
    }

}
