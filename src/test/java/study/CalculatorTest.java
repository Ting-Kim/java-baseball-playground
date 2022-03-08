package study;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.*;

public class CalculatorTest {

    static private Calculator calculator;

    @BeforeAll
    static void init() {
        calculator = new Calculator();
    }

    @Test
    void calculate() {
        // case 1: 일반적인 수식
        String input = "2 + 3 * 4 / 2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int actual = calculator.calculate();
        assertThat(10).isEqualTo(actual);

        // case 2: 계산식이 없는 단일 숫자 Input의 경우
        input = "0";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        actual = calculator.calculate();
        assertThat(0).isEqualTo(actual);
    }

    @Test
    void input() {
        // case: 잘못된 수식 Input
        String input = "2 + 3 * 4 2 2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        calculator.calculate() )
                .withMessageContaining("Invalid expression");
    }
}
