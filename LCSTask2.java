package pkg221_lab03;

import java.util.HashMap;
import java.util.Scanner;

public class LCSTask2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        int spacecounter1 = 0;
        int spacecounter2 = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == ' ') {
                spacecounter1++;
            }
        }
        for (int j = 0; j < s2.length(); j++) {
            if (s2.charAt(j) == ' ') {
                spacecounter2++;
            }

        }

        String x[] = s1.split(" ", spacecounter1 + 1);
        String y[] = s2.split(" ", spacecounter2 + 1);
        lcs(x, y);
    }

    static void lcs(String x[], String y[]) {
        HashMap<String, String> map = new HashMap<>();
        map.put("M", "monkeys");
        map.put("B", "because");
        map.put("W", "wearing");
        map.put("O", "of");
        map.put("C", "coats");
        map.put("E", "evolution");
        map.put("A", "are");
        map.put("R", "results");
        map.put("D", "doctors");
        map.put("P", "eruption");
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
        System.out.println(L[m][n]);

        for (int k = 0; k < store; k++) {

            System.out.print(map.get((lcs[k])) + " ");
        }

    }
}
