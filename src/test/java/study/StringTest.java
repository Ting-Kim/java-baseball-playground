package study;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StringTest {
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    @Test
    void split() {
        String[] actual = "1,2".split(",");
        String[] actual2 = "1".split(",");
        assertThat(actual).contains("1");
        assertThat(actual2).containsExactly("1");
    }

    @Test
    void substring() {
        String actual = "(1,2)".substring(1, 4);
        assertThat(actual).isEqualTo("1,2");
    }

    @Test
    void charAt() {
        String actual = "abc";
        assertThat(actual.charAt(1)).isEqualTo('b');
        assertThatExceptionOfType(StringIndexOutOfBoundsException.class).isThrownBy(
                () -> actual.charAt(3))
                .withMessage("String index out of range: 3")
                .withMessageContaining("String index out of range: ");
    }
}
