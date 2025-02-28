package course2.lesson8Lambda;

import java.util.Optional;

public class AppOptional {

    public static void main(String[] args) {
        Optional<String> optional = Optional.of("str");

        // optional.isEmpty(); - false
        // optional.isPresent() - true
        // optional.get() - Достать из Optional
        // optional.map(s -> s.toUpperCase());  - можно сразу работать как со стримами

        String fromOpt = optional.orElse("EmptyString");

        System.out.println(optional.get());
        System.out.println(fromOpt);
    }
}
