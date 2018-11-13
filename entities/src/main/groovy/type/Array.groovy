package type

import format.Formatter
import groovy.transform.Canonical
import groovy.transform.builder.Builder

@Builder
@Canonical
class Array implements Type {
    Type type

    @Override
    String createSqlPart() {
        String type = this.type.basic() ? type.createSqlPart() : createComplexSql(type)
        "array<${type}>"
    }

    @Override
    boolean basic() {
        return false
    }

    private static String createComplexSql(Type type) {
        new Formatter("${type.createSqlPart()}").indent().addeNewLines().format()
    }
}
