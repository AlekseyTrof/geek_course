package course0.yandex;

import java.io.*;

public class MainE {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        int Q = Integer.parseInt(br.readLine());
        String[] queries = new String[Q];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            queries[i] = br.readLine();
        }

        for (String query : queries) {
            int actions = 0;
            int totalPrefixLength = 0;

            for (String word : words) {
                actions++;
                int prefixLength = getCommonPrefixLength(word, query);
                totalPrefixLength += prefixLength;

                if (word.equals(query)) {
                    break;  // поиск прекращается, если слово найдено
                }
            }

            result.append(actions + totalPrefixLength).append("\n");
        }

        System.out.print(result);
    }

    private static int getCommonPrefixLength(String a, String b) {
        int length = Math.min(a.length(), b.length());
        for (int i = 0; i < length; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return i;
            }
        }
        return length;
    }
}
