package format

class Indenting {

    private static final int INDENT_NUM_SPACES = 2

    static String indent(String toIndent, int level) {
        String indentSpaces = " " * (level * INDENT_NUM_SPACES)
        "${indentSpaces}${toIndent}"
    }

}
