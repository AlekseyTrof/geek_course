package course2.lesson3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task1 {

    /**
     * Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и
     * вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
     * Посчитать, сколько раз встречается каждое слово.
     * @param args
     */
    public static void main(String[] args) {
        List<String> wordsArray = new ArrayList<>(Arrays.asList("one", "two", "three", "four", "five", "six",
                "six", "seven", "eight", "nine", "ten", "ten"));

        // Печатаем исходный массив
        System.out.println("Исходный массив: " + wordsArray);

        // Создаем список для хранения уникальных слов
        List<String> uniqueWords = new ArrayList<>();
        // Создаем карту для подсчета частоты появления слов
        Map<String, Integer> wordCount = new HashMap<>();

        // Проходим по каждому слову в массиве
        for (String word : wordsArray) {
            // Добавляем слово в список уникальных слов если его там нет
            if (!uniqueWords.contains(word)) {
                uniqueWords.add(word);
            }

            // Обновляем счетчик для текущего слова
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // Выводим список уникальных слов
        System.out.println("Список уникальных слов: " + uniqueWords);

        // Выводим количество вхождений каждого слова
        System.out.println("Количество вхождений каждого слова:");
        for (String uniqueWord : uniqueWords) {
            System.out.println(uniqueWord + ": " + wordCount.get(uniqueWord));
        }
    }
}
