package type

import groovy.transform.Canonical
import groovy.transform.builder.Builder

@Builder
@Canonical
class StringType implements Type {

    @Override
    String createSqlPart() {
        "string"
    }

    @Override
    boolean basic() {
        return true
    }
}
