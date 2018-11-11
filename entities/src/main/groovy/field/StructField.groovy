package field

import format.Indenting
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@ToString
class StructField implements Field {
    String name
    Fields fields
    int level
    boolean isLast

    @Override
    String createSqlPart() {
        String indent = " " * (level * 2)
        String ending = isLast ? "" : ","
        String closingBracket = Indenting.indent(">", level - 1)

        "${indent}${name} struct<\n${fields.createSqlPart()}\n${closingBracket}${ending}"
    }
}
