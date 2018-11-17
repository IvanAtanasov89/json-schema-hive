package field

import format.SQLFormatter
import groovy.transform.builder.Builder

@Builder
class CreateStatement {

    String table
    Fields fields

    String createSqlPart() {
        String fieldsSqlPart = new SQLFormatter(fields.createSqlPart()).indent().format()
        "CREATE TABLE ${table} (\n${fieldsSqlPart}\n);"
    }
}
