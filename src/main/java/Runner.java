import com.fasterxml.jackson.databind.ObjectMapper;
import field.CreateStatement;
import field.Fields;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;

public class Runner {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    public static String createSql(String jsonSchemaPath, String tableName) throws IOException {
        String jsonSchema = readFile(jsonSchemaPath);
        Fields fields = SchemaLooper.readProperties(MAPPER.readTree(jsonSchema));
        return CreateStatement.builder().fields(fields).table(tableName).build().createSqlPart();
    }

    private static String readFile(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        return IOUtils.toString(fis, "UTF-8");
    }
}
