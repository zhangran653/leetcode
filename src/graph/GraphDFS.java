package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphDFS {
    private List<Integer> order = new ArrayList<>();
    private Graph graph;
    private Set<Integer> visited = new HashSet<>();

    public GraphDFS(Graph graph) {
        this.graph = graph;
        for (int v = 0; v < graph.V(); v++) {
            if (!visited(v)) {
                dfs(v);
            }
        }
    }

    private boolean visited(int v) {
        return visited.contains(v);
    }


    private void dfs(int v) {
        visited.add(v);
        order.add(v);

        for (int w : graph.adj(v)) {
            if (!visited(w)) {
                dfs(w);
            }
        }
    }

    public Iterable<Integer> order() {
        return this.order;
    }

    public static void main(String[] args) {
        Graph g = new Graph("src/graph/g.txt");
        GraphDFS dfs = new GraphDFS(g);
        System.out.println(dfs.order());
    }


}
