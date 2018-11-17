package field;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FieldsTest {

    @Mock private Field field1;
    @Mock private Field field2;

    @Test
    void createSqlPart() {
        when(field1.createSqlPart()).thenReturn("hahah1");
        when(field2.createSqlPart()).thenReturn("hahah2");
        Fields fields = new Fields();
        fields.addField(field1).addField(field2);

        String expected = "hahah1\nhahah2";
        String actual = fields.createSqlPart();

        assertThat(actual, is(expected));
    }
}
