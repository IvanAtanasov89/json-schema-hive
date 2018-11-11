package field

import format.Indenting
import format.LineEnding
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@ToString
class BasicField implements Field {
    String name, type
    int level
    boolean isLast

    @Override
    String createSqlPart() {
        LineEnding.addEnding(Indenting.indent("$name $type", level), isLast)
    }
}
