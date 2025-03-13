package course0;

class Kata {

    public static String accum(String s) {
        String[] strings = s.toLowerCase().split("");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                result.append(strings[i].toUpperCase());
            } else {
                result.append("-");
                result.append(charsOfTime(strings[i], i));
            }
        }
        return result.toString();
    }

    public static String charsOfTime(String str, int times) {
        String result = "";
        for (int i = 0; i < times; i++) {
            if (i == 0) {
                result += str.toUpperCase();
            }
            result += str;
        }
        return result;
    }
}

