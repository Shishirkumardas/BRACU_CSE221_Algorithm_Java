//package pkg221_lab04;


import java.util.Scanner;

public class KnapsackTask1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalBudget =sc.nextInt();
        int totalPlayer =sc.nextInt();
        int price[] = new int[totalPlayer];
        int form[] = new int[totalPlayer];
        int v[][]=new int[totalPlayer+1][totalBudget+1];
        sc.nextLine();
        String player[]=new String[totalPlayer];
        String position[]=new String[totalPlayer];
         
        for (int i = 0; i < totalPlayer; i++) {
            String s=sc.nextLine();
            String[] string =s.split(", ",4);
            //System.out.println(string[2]);
            player[i]=string[0];
            position[i]=string[3];
            price[i] = Integer.parseInt(string[1]);
            form[i] = Integer.parseInt(string[2]);
            
        }

        knapsack(v,price, form, totalPlayer, totalBudget);

        System.out.println("Bought Players:");
        FinalPlayers(v, totalPlayer, totalBudget, form,player,position);
        System.out.println("Maximum summation of form: " + v[totalPlayer][totalBudget]);
    }

    public static int knapsack(int v[][],int[] price, int[] form, int n, int w) {
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if (j >= price[i - 1]) {
                    v[i][j] = Math.max(v[i - 1][j],
                            form[i - 1] + v[i - 1][j - price[i - 1]]);
                } else {
                    v[i][j] = v[i - 1][j];
                }
            }
        }

        return v[n][w];
    }

    static void FinalPlayers(int[][] graph, int n, int w, int[] form,String player[],String position[]) {
       

        String string = "";
        int a = graph[n][w];
        for (int r = n - 1; r >= 0; r--) {
            boolean b = false;
            for (int c = 0; c <= w; c++) {
                if (graph[r][c] == a) {
                    b = true;
                    break;
                }
            }
            if (b == false) {
                string += "<-" + player[r];
                a = a - form[r];
            }
        }
        System.out.println(string);

    }

}
