package format;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class FormatterTest {

    @Test
    @DisplayName("Should indent each line with 2 characters")
    void indent() {
        String expected = "  hello\n  kaboom";
        String actual = new Formatter("hello\nkaboom")
                .indent()
                .format();

        assertThat(actual, is(expected));
    }

    @Test
    @DisplayName("Ensure that a comma is not added when it is the last element")
    void addEndingWhenLast() {
        String expected = "hello,\nkaboom";
        String actual = new Formatter("hello,\nkaboom")
                .addEnding(true)
                .format();

        assertThat(actual, is(expected));
    }

    @Test
    @DisplayName("Ensure that a comma is added when it is the last element")
    void addEndingWhenNotLast() {
        String expected = "hello,\nkaboom,";
        String actual = new Formatter("hello,\nkaboom")
                .addEnding(false)
                .format();

        assertThat(actual, is(expected));
    }
}
