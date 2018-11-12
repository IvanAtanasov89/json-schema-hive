package format

class Formatter {

    private static final int INDENT_NUM_SPACES = 2

    String toFormat

    Formatter(final String toFormat) {
        this.toFormat = toFormat
    }

    Formatter indent() {
        String indentSpaces = " " * INDENT_NUM_SPACES
        toFormat = "${indentSpaces}${toFormat}".split('\\n').join("\n${indentSpaces}")
        this
    }

    Formatter addEnding(boolean isLast) {
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
