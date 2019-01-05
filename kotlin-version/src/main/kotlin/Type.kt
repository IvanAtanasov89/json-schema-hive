interface Type {
    fun createSqlPart(): String
    fun basic(): Boolean
}

class ArrayType(private val type: Type) : Type {
    override fun createSqlPart(): String {
        val type = if (this.type.basic()) this.type.createSqlPart() else createComplexSql()
        return "array<$type>"
    }

    override fun basic(): Boolean {
        return false
    }

    private fun createComplexSql(): String {
        return SqlFormatter(toFormat = type.createSqlPart()).indent().addNewLines().format()
    }

}

class StringType: Type {
    override fun createSqlPart(): String {
        return "string"
    }

    override fun basic(): Boolean {
        return true
    }
}

class Struct(private val fields: Fields) : Type {
    override fun createSqlPart(): String {
        return "struct<${SqlFormatter(fields.createSqlPart()).indent().addNewLines().format()}>"
    }

    override fun basic(): Boolean {
        return false
    }
}
