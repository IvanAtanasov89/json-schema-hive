interface Field {
    fun createSqlPart() : String
}

class BasicField(private val name: String, private val isLast: Boolean) : Field {
    override fun createSqlPart(): String {
        return ""
    }
}

class Fields: Field {
    private var fields = ArrayList<Field>()

    fun addField(field: Field): Fields {
        fields.add(field)
        return this
    }

    override fun createSqlPart(): String {
        return fields.joinToString(separator = "\n", transform = {it.createSqlPart()})
    }
}
