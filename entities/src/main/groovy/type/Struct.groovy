package type

import field.Fields
import format.Formatter
import groovy.transform.Canonical
import groovy.transform.builder.Builder

@Builder
@Canonical
class Struct implements Type {
    Fields fields

    @Override
    String createSqlPart() {
        String nestedFields = new Formatter(fields.createSqlPart()).indent().format()
        "struct<\n${nestedFields}\n>"
    }

    @Override
    boolean basic() {
        return false
    }
}
