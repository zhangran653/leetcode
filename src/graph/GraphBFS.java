package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {
    private Graph graph;
    private int[] visited;
    private ArrayList<Integer> order = new ArrayList<>();

    public GraphBFS(Graph graph) {
        this.graph = graph;
        visited = new int[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            if (!visited(v)) {
                bfs(v);
            }
        }
    }

    private boolean visited(int v) {
        return visited[v] == 1;
    }


    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = 1;

        while (!queue.isEmpty()) {
            int v = queue.remove();
            order.add(v);
            for (int w : graph.adj(v)) {
                if (!visited(w)) {
                    queue.add(w);
                    visited[w] = 1;
                }
            }

        }

    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        Graph g = new Graph("src/graph/g3.txt");
        GraphBFS bfs = new GraphBFS(g);
        System.out.println(bfs.order());

    }
}
