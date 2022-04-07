/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg221_lab_2;

import java.io.File;
import java.util.*;

public class Task01 {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            int Vertex = sc.nextInt();
            int Edge = sc.nextInt();
            int hotelPosition = sc.nextInt();
            int totalMission = sc.nextInt();
            int[][] matrix = new int[Vertex][Vertex];
            for (int i = 0; i < Edge; i++) {
                int x = sc.nextInt() - 1;
                int y = sc.nextInt() - 1;
                int marker = sc.nextInt();
                matrix[x][y] = marker; // since it's directed
            }
            for (int i = 1; i <= totalMission; ++i) {
                int start = sc.nextInt();
                int end = sc.nextInt();
                DijkstraImp djk1 = new DijkstraImp(Vertex, matrix);
                djk1.dijkstra(start - 1);
                boolean flag1 = false, flag2 = false;
                int totalMarkerValue = 0;
                if (djk1.d[hotelPosition - 1] != Integer.MAX_VALUE) {
                    flag1 = true;
                    totalMarkerValue = djk1.d[hotelPosition - 1];
                    DijkstraImp djk2 = new DijkstraImp(Vertex, matrix);
                    djk2.dijkstra(hotelPosition - 1);
                    if (djk2.d[end - 1] != Integer.MAX_VALUE) {
                        flag2 = true;
                        totalMarkerValue += djk2.d[end - 1];
                    }
                }
                System.out.println("Case " + i + ":");
                if (flag1 == false || flag2 == false) {
                    System.out.println("Be seeing ya, John");
                } else {
                    System.out.println(totalMarkerValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Node implements Comparator<Node> {

    int node;
    int distance;

    public Node() {
    }

    public Node(int _node, int _distance) {
        node = _node;
        distance = _distance;
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

class DijkstraImp {

    int n;//num of nodes
    int g[][];
    int d[];//distance array
    PriorityQueue<Node> pq;

    public DijkstraImp() {
    }

    public DijkstraImp(int _n, int _g[][]) {
        n = _n;
        g = _g;
        d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = Integer.MAX_VALUE;
        }
        pq = new PriorityQueue<Node>(n, new Node());
    }

    void dijkstra(int s) {
        d[s] = 0;
        pq.add(new Node(s, d[s]));
        while (pq.isEmpty() != true) {
            int u = pq.remove().node;
            for (int v = 0; v < n; v++) {
                if (g[u][v] > 0 && d[v] > g[u][v] + d[u]) {
                    d[v] = g[u][v] + d[u];
                    pq.add(new Node(v, d[v]));
                }
            }
        }
    }
}
