package field

import format.Formatter
import groovy.transform.Canonical
import groovy.transform.builder.Builder

@Builder
@Canonical
class ArrayField implements Field {
    String name, type
    boolean isLast

    @Override
    String createSqlPart() {
        new Formatter("$name array<$type>").addEnding(isLast).format()
    }



    // Field
    // Type
}
