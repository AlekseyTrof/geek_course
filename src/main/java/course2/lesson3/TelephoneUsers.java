package course2.lesson3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TelephoneUsers {
    private Map<String, ArrayList<String>> users;

    public TelephoneUsers() {
        users = new HashMap<>();
    }

    public void add(String lastName, String numberPhone) {
        users.computeIfAbsent(lastName, num -> new ArrayList<>()).add(numberPhone);
    }

    public ArrayList<String> get(String lastName) {
        return users.getOrDefault(lastName, new ArrayList<>());
    }

}
