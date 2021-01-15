package graph;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Graph {
    private int V;
    private int E;
    private TreeSet<Integer>[] adj;

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Graph(String fileName) {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must > 0");
            }
            adj = new TreeSet[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<>();
            }
            E = scanner.nextInt();
            for (int i = 1; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                if (a == b) throw new IllegalArgumentException("Self Loop is Detected!");
                if (adj[a].contains(b)) throw new IllegalArgumentException("Parallel Edges are Detected!");
                adj[a].add(b);
                adj[b].add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }


    public void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + "is invalid");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V= %d, E= %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Graph araph = new Graph("src/graph/g.txt");
        System.out.println(araph);
    }
}
