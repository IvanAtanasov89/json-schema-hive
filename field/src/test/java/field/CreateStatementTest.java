package field;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreateStatementTest {

    @Mock private Fields fields;

    @Test
    void createSqlPart() {
        when(fields.createSqlPart()).thenReturn("one string,\ntwo int");
        CreateStatement createStatement = CreateStatement.builder().fields(fields).table("click").build();
        assertThat(createStatement.createSqlPart(), is("CREATE TABLE click (\n  one string,\n  two int\n);"));
    }
}
