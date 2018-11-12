package field

import format.Formatter
import groovy.transform.Canonical
import groovy.transform.builder.Builder

@Builder
@Canonical
class StructField implements Field {
    String name
    Fields fields
    boolean isLast

    @Override
    String createSqlPart() {
        String subFieldsPart = new Formatter(fields.createSqlPart())
                .indent()
                .format()

        new Formatter("${name} struct<\n${subFieldsPart}\n>")
                .addEnding(isLast)
                .format()
    }
}
