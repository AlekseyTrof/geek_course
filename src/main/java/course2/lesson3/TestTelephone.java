package course2.lesson3;

public class TestTelephone {
    public static void main(String[] args) {

        TelephoneUsers telephoneUsers = new TelephoneUsers();

        telephoneUsers.add("Alex", "111");
        telephoneUsers.add("Alex", "222");
        telephoneUsers.add("Alex2", "333");
        telephoneUsers.add("Alex3", "444");

        System.out.println(telephoneUsers.get("Alex"));
    }
}
