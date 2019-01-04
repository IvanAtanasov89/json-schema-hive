class SqlFormatter(private var toFormat: String) {
    private val INDENT_SPACES = 2
    private val INDENTATION = " ".repeat(INDENT_SPACES)

    fun indent(): SqlFormatter {
        toFormat = toFormat.split("\n").joinToString(prefix = INDENTATION, separator = "\n$INDENTATION")
        return this
    }

    fun addEnding(isLast: Boolean): SqlFormatter {
        toFormat += if (isLast) "" else ","
        return this
    }

    fun format(): String {
        return toFormat
    }
}
