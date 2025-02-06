package ru_geekbrains.course1.yandex;

import java.util.Scanner;

public class MainD {
    static final int MOD = 1000000007;
    static long[][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        String sequence = scanner.next();
        scanner.close();

        dp = new long[N + 1][N + 1];
        dp[0][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= N; j++) {
                if (dp[i][j] == 0) continue;

                char ch = sequence.charAt(i);
                int addOpen = 0, addClose = 0;

                if (ch == '?' || ch == '(') {
                    addOpen = 1; // We can place '('
                }
                if (ch == '?' || ch == '[') {
                    addOpen = 1; // We can place '['
                }
                if (ch == '?' || ch == '{') {
                    addOpen = 1; // We can place '{'
                }
                if (ch == '?' || ch == ')') {
                    addClose = 1; // We can place ')'
                }
                if (ch == '?' || ch == ']') {
                    addClose = 1; // We can place ']'
                }
                if (ch == '?' || ch == '}') {
                    addClose = 1; // We can place '}'
                }

                // Process open brackets
                if (addOpen > 0) {
                    dp[i + 1][j + 1] = (dp[i + 1][j + 1] + dp[i][j] * addOpen) % MOD;
                }

                // Process close brackets
                if (j > 0 && addClose > 0) {
                    dp[i + 1][j - 1] = (dp[i + 1][j - 1] + dp[i][j] * addClose) % MOD;
                }
            }
        }
        System.out.println(dp[N][0]);
    }
}
