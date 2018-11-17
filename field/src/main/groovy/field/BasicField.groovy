package field

import format.SQLFormatter
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
        new SQLFormatter("$name ${type.createSqlPart()}")
                .addEnding(isLast)
                .format()
    }
}
