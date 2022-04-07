package pkg221_lab04;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class Task02 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int w = Integer.parseInt(sc.nextLine());
        int trophy = Integer.parseInt(sc.nextLine());
        int weight[] = new int[trophy];
        double price[] = new double[trophy];
        String string[];
        for (int i = 0; i < trophy; i++) {
            string = sc.nextLine().split(", ");
            weight[i] = Integer.parseInt(string[1]);
            price[i] = Double.parseDouble(string[2]);
        }

        double graph[][] = knapsack(weight, price, trophy, w);
        System.out.println("Name of clubs whose trophies were sold:");
        FinalPlayer(graph, trophy, w, price);
        System.out.println("Maximum money he earned: " + graph[trophy][w] + " million");
    }

    public static double[][] knapsack(int[] weight, double[] price, int n, int w) {
        double graph[][] = new double[n + 1][w + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if (j >= weight[i - 1]) {
                    graph[i][j] = Math.max(graph[i - 1][j],
                            price[i - 1] + graph[i - 1][j - weight[i - 1]]);
                } else {
                    graph[i][j] = graph[i - 1][j];
                }
            }
        }

        return graph;
    }

    static void FinalPlayer(double[][] graph, int n, int w, double[] price) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "Chelsea");
        map.put(1, "Inter Milan");
        map.put(2, "Individual");
        map.put(3, "Real Madrid");
        map.put(4, "Porto");
        map.put(5, "Manchester United");

        String string = "";
        double a = graph[n][w];
        for (int r = n - 1; r >= 1; r--) {
            boolean b = false;
            for (int c = 0; c <= w; c++) {
                if (graph[r][c] == a) {
                    b = true;
                    break;
                }
            }
            if (b == false) {
                string = map.get(r) + "->" + string;
                a = a - price[r];

            }
        }

        System.out.println(string);

    }

}
