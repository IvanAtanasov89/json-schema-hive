package json.schema.hive

import com.fasterxml.jackson.databind.JsonNode
import kotlin.collections.Map.Entry

private const val INDENT_SPACES = 2
private val INDENTATION = " ".repeat(INDENT_SPACES)

class CreateStatement(private val tableName: String, private val fields: Fields) {
    fun createSql(): String {
        return "CREATE TABLE $tableName (${SqlFormatter(fields.createSqlPart()).indent().addNewLines().format()});"
    }
}

class SqlFormatter(private var toFormat: String) {
    fun indent(): SqlFormatter {
        toFormat = toFormat.split("\n").joinToString(prefix = INDENTATION, separator = "\n$INDENTATION")
        return this
    }

    fun addEnding(isLast: Boolean): SqlFormatter {
        toFormat += if (isLast) "" else ","
        return this
    }

    fun addNewLines(): SqlFormatter {
        toFormat = "\n$toFormat\n"
        return this
    }

    fun format(): String {
        return toFormat
    }
}

fun read(node: JsonNode): Fields {
    val fields = Fields()
    val nodes = node.get("properties").fields()
    while (nodes.hasNext()) {
        fields.addField(createField(nodes))
    }
    return fields
}

private fun createField(nodes: Iterator<Entry<String, JsonNode>>): Field {
    val entry = nodes.next()
    val fieldName = entry.key
    val isLast = !nodes.hasNext()
    return BasicField(name = fieldName, type = readType(entry.value), isLast = isLast)
}

private fun readType(node: JsonNode): Type {
    val type = node.get("type").asText()
    return when (type) {
        "object" -> Struct(fields = read(node))
        "array" -> ArrayType(type = readType(node.get("items")))
        else -> StringType()
    }
}
