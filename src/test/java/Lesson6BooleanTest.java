import course3.lesson6.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Lesson6BooleanTest {

    @Test
    void testTrueOneAndFour() {
        int[] numbers = {1, 2, 3, 4, 5, 6};
        boolean result = Main.numbersFourAndOne(numbers);
        Assertions.assertTrue(result);
    }

    @Test
    void testFalseNoOne() {
        int[] numbers = {2, 3, 4, 5, 6};
        boolean result = Main.numbersFourAndOne(numbers);
        Assertions.assertFalse(result);
    }

    @Test
    void testFalseNoFour() {
        int[] numbers = {1, 2, 3, 5, 6};
        boolean result = Main.numbersFourAndOne(numbers);
        Assertions.assertFalse(result);
    }

    @Test
    void testFalseNoOneAndFour() {
        int[] numbers = {2, 3, 5, 6, 2, 6, 8, 9, 3, 3, 3, 3};
        boolean result = Main.numbersFourAndOne(numbers);
        Assertions.assertFalse(result);
    }

    // Этот метод будет предоставлять параметры для тестирования с параметризованными значениями
    static Stream<Object[]> expectedResultsProvider() {
        return Stream.of(new Object[]{
                new int[]{1, 2, 3, 4}, true
        }, new Object[]{
                new int[]{1, 4, 5}, true
        }, new Object[]{
                new int[]{2, 3, 5}, false
        }, new Object[]{
                new int[]{1, 3, 5}, false
        }, new Object[]{
                new int[]{4, 5, 6}, false
        }, new Object[]{
                new int[]{1, 2, 4, 6}, true
        });
    }

    @ParameterizedTest
    @MethodSource("expectedResultsProvider")
    void testNumbersFourAndOne(int[] numbers, boolean expected) {
        Assertions.assertEquals(expected, Main.numbersFourAndOne(numbers));
    }
}
