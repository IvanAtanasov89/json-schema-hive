package type

import field.Fields
import format.SQLFormatter
import groovy.transform.Canonical
import groovy.transform.builder.Builder

@Builder
@Canonical
class Struct implements Type {
    Fields fields

    @Override
    String createSqlPart() {
        String nestedFields = new SQLFormatter(fields.createSqlPart()).indent().format()
        "struct<\n${nestedFields}\n>"
    }

    @Override
    boolean basic() {
        return false
    }
}
