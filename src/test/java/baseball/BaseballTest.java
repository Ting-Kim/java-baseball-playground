package baseball;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class BaseballTest {

    private Baseball baseball;

    @BeforeEach
    void initEach() {
        Counter counter = new PersonCounter("592");
        Judge judge = new Judge();
        baseball = new Baseball(counter, judge);
    }

    @Test
    void hasNumber() {
        Counter counter = baseball.getCounter();
        char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        assertThat(numbers).contains(counter.getNumbers().toCharArray());
        assertThat(counter.getNumbers()).hasSize(3);
    }

    @ParameterizedTest
    @MethodSource("provideExpectNumbers")
    void judge(String expectNumbers, String expect) {
        String actual = baseball.judge(expectNumbers);
        assertThat(actual).isEqualTo(expect);
    }

    @ParameterizedTest
    @MethodSource("provideExceptionNumbers")
    void judgeException(String expectNumbers) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                    baseball.judge(expectNumbers))
                .withMessageContaining("Invalid numbers");
    }

    private static Stream<Arguments> provideExceptionNumbers() {
        return Stream.of(
                Arguments.of("12#"),
                Arguments.of("가나9"),
                Arguments.of("1151"),
                Arguments.of("53912311"),
                Arguments.of("=3="),
                Arguments.of("009"),
                Arguments.of("000"),
                Arguments.of("100"),
                Arguments.of("120"),
                Arguments.of("555")
        );
    }

    private static Stream<Arguments> provideExpectNumbers() {
        return Stream.of(
                Arguments.of("123", "1볼"),
                Arguments.of("259", "3볼"),
                Arguments.of("134", "낫싱"),
                Arguments.of("539", "1볼 1스트라이크"),
                Arguments.of("593", "2스트라이크"),
                Arguments.of("529", "2볼 1스트라이크"),
                Arguments.of("592", "3스트라이크")
        );
    }
}
