package graph;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdjMatrix {
    private int V;
    private int E;
    private int[][] adj;

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public AdjMatrix(String fileName) {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must > 0");
            }
            adj = new int[V][V];
            E = scanner.nextInt();
            for (int i = 1; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                if (a == b) throw new IllegalArgumentException("Self Loop is Detected!");
                if (adj[a][b] == 1) throw new IllegalArgumentException("Parallel Edges are Detected!");
                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v][w] == 1;
    }

    public List<Integer> adj(int v) {
        validateVertex(v);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1) {
                res.add(i);
            }
        }
        return res;
    }

    public int degree(int v) {
        validateVertex(v);
        int degree = 0;
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1) {
                degree++;
            }
        }
        return degree;
    }


    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + "is invalid");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V= %d, E= %d\n", V, E));
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(String.format("%d ", adj[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("src/graph/g.txt");
        System.out.println(adjMatrix);
    }
}
