import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class CreateIntegrationTest {

    @Test
    @DisplayName("Integration test for reading a JSON schema file and creating a create statment")
    void createSql() throws IOException {
        String expected = TestHelper.readResource("src/test/resources/expectedSql.txt").trim();
        String actual = Runner.createSql("src/test/resources/integrationSchema.json", "click");
        assertThat(actual, is(expected));
    }

}
