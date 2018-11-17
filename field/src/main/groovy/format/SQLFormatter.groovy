package format

class SQLFormatter {

    private static final int INDENT_NUM_SPACES = 2

    String toFormat

    SQLFormatter(final String toFormat) {
        this.toFormat = toFormat
    }

    SQLFormatter indent() {
        String indentSpaces = " " * INDENT_NUM_SPACES
        toFormat = "${indentSpaces}${toFormat}".split('\\n', -1).join("\n${indentSpaces}")
        this
    }

    SQLFormatter addeNewLines() {
        toFormat = "\n$toFormat\n"
        this
    }

    SQLFormatter addEnding(boolean isLast) {
        toFormat = "${toFormat}${commaOrNoComma(isLast)}"
        this
    }

    String format() {
        toFormat
    }

    private static String commaOrNoComma(boolean isLast) {
        isLast ? "" : ","
    }
}
