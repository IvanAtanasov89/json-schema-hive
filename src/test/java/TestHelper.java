import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;

public class TestHelper {

    public static String readResource(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        return IOUtils.toString(fis, "UTF-8");
    }
}
