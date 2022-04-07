package pkg221_lab03;

import java.util.Scanner;

class LCSTask1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        int spaceCounter1 = 0;
        int spaceCounter2 = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == ' ') {
                spaceCounter1++;
            }
        }
        for (int j = 0; j < s2.length(); j++) {
            if (s2.charAt(j) == ' ') {
                spaceCounter2++;
            }

        }

        String x[] = s1.split(" ", spaceCounter1 + 1);
        String y[] = s2.split(" ", spaceCounter2 + 1);
        lcs(x, y);
    }

    static void lcs(String x[], String y[]) {
        int m = x.length;
        int n = y.length;
        int[][] L = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    L[i][j] = 0;
                } else if (x[i - 1].equals(y[j - 1])) {
                    L[i][j] = L[i - 1][j - 1] + 1;
                } else {
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
                }
            }
        }
        int accuracy = L[m][n] * 100 / m;
        int value = L[m][n];
        int store = value;
        String[] lcs = new String[value + 1];
        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            if (x[i - 1].equals(y[j - 1])) {
                lcs[value - 1] = x[i - 1];
                i--;
                j--;
                value--;
            } else if (L[i - 1][j] > L[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        for (int k = 0; k < store; k++) {
            System.out.print(lcs[k]);
        }
        System.out.println();
        System.out.println(accuracy + "% PASSED");
    }
}
