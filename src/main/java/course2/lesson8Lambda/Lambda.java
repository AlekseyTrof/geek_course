package course2.lesson8Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lambda {
    /*
    ------ТЕРМИНАЛЬНЫЕ и НЕТЕРМЕНАЛЬНЫЕ------

    skip(n) - пропускает n элементов
    limit(n) - возьмет только n элементов, остальные отбросит

    filter (Например, если строка начинается на "/",
           если число меньше 0)
           filter(integer -> integer % 2 == 0)

    map - превращает один объект в другой (Например, умножить каждое число на 1000
    map(integer -> integer * 1000)

    peek - позволяет просто заглянуть в поток, посмотреть на данные
    peek(i -> sout(i));

    sorted() - Сортирует

    distinct() - оставляет только уникальные элементы

    Терминальные:
    collect - собирает всё в кучу и возвращает в виде списка или массива...
    collect(Collectors.toList());
    collect(Collectors.joining("<->"));   ---соединяет строки и ставит между ними что то

    forEach - выводит каждый элемент массива или списка
    forEach(i -> System.out.print(i + " "));

    count - посчитать количество элементов
    count();

    max(Comparator.naturalOrder()).get;
    min(Comparator.naturalOrder()).get;
     */

    public static void main(String[] args) {
        Random random = new Random();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(random.nextInt(100));
        }
        System.out.println(list);

        List<Integer> list2 = list.stream()
                .limit(50)
                //.peek(integer -> System.out.print(integer))
                .filter(integer -> integer % 2 == 0)
                .map(integer -> integer * 1000)
                .collect(Collectors.toList());
        System.out.println(list2);

        String s = list.stream()
                .limit(50)
                //.peek(integer -> System.out.print(integer))
                .filter(integer -> integer % 2 == 0)
                .sorted()
                .distinct()
                .map(integer -> integer * 1000)
                .map(integer -> String.valueOf(integer))
                .collect(Collectors.joining(", "));
        System.out.println(s);


        /*
        Работа со строками
         */
        Stream<String> string = Stream.of("dddd", "aaa", "aaaa", "aa", "aaaa", "bbb", "bbbb", "bbbbb", "ccc", "ccc", "cccc");
        List<String> collect = string
                .filter(str -> str.length() == 4)
                .map(str -> str.toUpperCase())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(collect);

        Stream<String> string1 = Stream.of("dddd", "aaa", "aaaa", "aa", "aaaa", "bbb", "bbbb", "bbbbb", "ccc", "ccc", "cccc");
        boolean isAll4 = string1
                .filter(str -> str.length() == 4)
                .map(str -> str.toUpperCase())
                .allMatch(str -> str.length() == 4);
        System.out.println(isAll4);

        Stream<String> string2 = Stream.of("dddd", "aaa", "aaaa", "aa", "aaaa", "bbb", "bbbb", "bbbbb", "ccc", "ccc", "cccc");
        boolean isMatch = string2
                .map(str -> str.toUpperCase())
                .noneMatch(str -> str.length() > 6);
        System.out.println(isMatch);

        Stream<String> string3 = Stream.of("dddd", "aaa", "aaaa", "aa", "aaaa", "bbb", "bbbb", "bbbbb", "ccc", "ccc", "cccc");
        Map<String, Integer> integerMap = string3
                .collect(Collectors.toMap(Function.identity(), str -> 1, (v1, v2) -> v1 + 1));
        System.out.println(integerMap);
    }
}
