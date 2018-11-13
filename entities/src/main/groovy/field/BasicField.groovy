package field

import format.Formatter
import groovy.transform.Canonical
import groovy.transform.builder.Builder
import type.Type

@Builder
@Canonical
class BasicField implements Field {
    String name
    Type type
    boolean isLast

    @Override
    String createSqlPart() {
        new Formatter("$name ${type.createSqlPart()}")
                .addEnding(isLast)
                .format()
    }
}
