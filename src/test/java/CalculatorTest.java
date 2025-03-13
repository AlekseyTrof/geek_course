import course3.lesson6.Calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void init() {
        calculator = new Calculator();
    }

    @Test
    void testSum() {
        Assertions.assertEquals(15,calculator.add(10,5));
    }

    @Test
    @Disabled("Деление пока тестировать не нужно")
    void testDiv() {
        Assertions.assertEquals(1, calculator.div(2, 2));
    }

    @Test
    @DisplayName("Вычитание")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testSub() {
        Assertions.assertEquals(3, calculator.sub(5, 2));
    }

    @Test
    void testMul() {
        Assertions.assertEquals(9, calculator.mul(3, 3));
    }

    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "7, 5, 12",
            "12, 12, 24"
    })
    @ParameterizedTest
    public void massTestAdd(int a, int b, int result) {
        Assertions.assertEquals(result, calculator.add(a, b));
    }

    @ParameterizedTest
    @MethodSource("dataForAddOperation")
    public void testAddOperation(int a, int b, int result) {
        Assertions.assertEquals(result, calculator.add(a, b));
    }
    public static Stream<Arguments> dataForAddOperation() {
        List<Arguments> out = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            int a = (int)(Math.random() * 1000);
            int b = (int)(Math.random() * 1000);
            int result = a + b;
            out.add(Arguments.arguments(a, b, result));
        } return out.stream();
    }

}
