package field

import groovy.transform.Canonical

@Canonical
class Fields implements Field {
    List<Field> fields = new ArrayList()

    Fields addField(Field field) {
        fields.add(field)
        this
    }

    @Override
    String createSqlPart() {
        fields*.createSqlPart().join("\n")
    }
}
