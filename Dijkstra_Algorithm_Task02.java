/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg221_lab_2;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Task02 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertex = sc.nextInt();
        int edge = sc.nextInt();
        int matrix[][] = new int[vertex][vertex];
        String blocked[] = new String[vertex];
        for (int i = 0; i < edge; i++) {
            int j = sc.nextInt();
            int k = sc.nextInt();
            int weight = sc.nextInt();
            matrix[j][k] = weight;
        }
        int source = sc.nextInt();
        int goal = sc.nextInt();
        int b1 = sc.nextInt();
        int b2 = sc.nextInt();
        int b3 = sc.nextInt();
        int b4 = sc.nextInt();
        for (int i = 0; i < vertex; i++) {
            if (i == b1 || i == b2 || i == b3 || i == b4) {
                blocked[i] = "Yellow";
            }
        }

        String[] areas = {"Mouchak", "Panthapath", "Rampura", "Shahbagh", "Dhanmondi", "Lalmatia", "Badda", "Hatirjheel", "Nilkhet", "TSC", "Dhaka University", "BUET"};
        DijkstraImp1 djk = new DijkstraImp1(vertex, matrix, blocked);
        djk.dijkstra1(source, goal);
        int k = goal;
        int count = 0;
        while (djk.parent[k] != 0) {
            count++;
            k = djk.parent[k];
        }
        String[] c = new String[count + 2];
        c[0] = areas[goal];
        c[c.length - 1] = areas[source];
        k = goal;
        for (int i = 1; i < c.length - 1; i++) {
            k = djk.parent[k];
            c[i] = areas[k];
        }
        for (int i = c.length - 1; i > 0; i--) {
            System.out.print(c[i] + "->");
        }
        System.out.println(c[0]);
        System.out.println("Path Cost: " + djk.d[goal]);
    }
}

class Node2 implements Comparator<Node> {

    int node;
    int distance;

    public Node2(int n, int d) {
        node = n;
        distance = d;
    }

    public Node2() {

    }

    public int compare(Node n1, Node n2) {
        if (n1.distance < n2.distance) {
            return -1;
        } else if (n1.distance > n2.distance) {
            return 1;
        }
        return 0;
    }
}

class DijkstraImp1 {

    int n;
    int g[][];
    int d[];
    int parent[];
    String b[];
    PriorityQueue<Node> pq;

    public DijkstraImp1(int e, int a[][]) {
        n = e;
        g = a;
        d = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = Integer.MAX_VALUE;
            parent[i] = 0;
        }
        pq = new PriorityQueue<Node>(n, new Node());
    }

    public DijkstraImp1(int e, int a[][], String c[]) {
        n = e;
        g = a;
        b = c;
        d = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = Integer.MAX_VALUE;
            parent[i] = 0;
        }
        pq = new PriorityQueue<Node>(n, new Node());
    }

    public int dijkstra(int s, int d1) {
        d[s] = 0;
        pq.add(new Node(s, d[s]));
        while (pq.isEmpty() != true) {
            int u = pq.remove().node;
            for (int v = 0; v < n; v++) {
                if (g[u][v] > 0 && d[v] > g[u][v] + d[u]) {
                    d[v] = g[u][v] + d[u];
                    parent[v] = u;
                    pq.add(new Node(v, d[v]));
                }
            }
        }
        return d[d1];
    }

    public void dijkstra1(int s, int d2) {
        d[s] = 0;
        pq.add(new Node(s, d[s]));
        while (pq.isEmpty() != true) {
            int u = pq.remove().node;
            if (b[u] == "Yellow") {

            } else {
                for (int v = 0; v < n; v++) {
                    if (g[u][v] > 0 && d[v] > g[u][v] + d[u]) {
                        d[v] = g[u][v] + d[u];
                        parent[v] = u;
                        pq.add(new Node(v, d[v]));
                    }
                }
            }
        }
    }
}
