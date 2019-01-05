package json.schema.hive

import com.fasterxml.jackson.databind.ObjectMapper
import com.xenomachina.argparser.ArgParser
import java.io.File

fun main(args: Array<String>) {
    ArgParser(args).parseInto(::MyArgs).run {
        println("Using dis: $file!")
        val schema = File(file).readText(Charsets.UTF_8)
        val mapper = ObjectMapper()
        val fields = read(mapper.readTree(schema))
        val createStatement = CreateStatement(tableName, fields)
        println(createStatement.createSql())
    }
}

class MyArgs(parser: ArgParser) {
    val file by parser.storing("-f", "--file", help = "enable verbose mode")
    val tableName by parser.storing("-t", "--table_name", help = "name of the table")
}
