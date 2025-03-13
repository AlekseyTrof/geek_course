import course3.lesson6.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Lesson6ArraysTest {

    @ParameterizedTest
    @MethodSource("provideArraysForTesting")
    void testArray(int[] a, int[] result) {
        Assertions.assertArrayEquals(result, Main.array(a));
    }

    static Stream<Arguments> provideArraysForTesting() {
        // Возвращаем поток аргументов для параметризованных тестов
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4}, new int[]{}),              // Элементы после одной четверки
                Arguments.of(new int[]{1, 2, 3, 4, 4, 5}, new int[]{5}),       // Элементы после нескольких четверок
                Arguments.of(new int[]{4, 5, 4, 6}, new int[]{6}),             // Когда последняя четверка не в конце
                Arguments.of(new int[]{}, new int[]{}),                        // Пустой массив
                Arguments.of(new int[]{1, 4, 2, 4, 3}, new int[]{3}),          // Четверки не в конце
                Arguments.of(new int[]{4, 4, 4}, new int[]{}),                 // Только четверки
                Arguments.of(new int[]{5, 6, 7, 4}, new int[]{})               // Четверка в конце
        );
    }

    @Test
    void testArrayWithNoFours() {
        // Проверяем массив без четверок
        int[] input = {1, 2, 3, 5};
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            Main.array(input);
        });
        Assertions.assertEquals("В массиве нет четверок", thrown.getMessage());
    }

    @Test
    void testArrayWithFoursAtEnd() {
        // Проверяем массив, где четверки находятся в конце
        int[] input = {1, 2, 3, 4};
        int[] expected = {}; // Ожидаем, что после последней четверки ничего не остается
        Assertions.assertArrayEquals(expected, Main.array(input));
    }

}
