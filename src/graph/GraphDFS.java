package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphDFS {
    private List<Integer> pre = new ArrayList<>();
    private List<Integer> post = new ArrayList<>();
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
        pre.add(v);

        for (int w : graph.adj(v)) {
            if (!visited(w)) {
                dfs(w);
            }
        }
        post.add(v);
    }

    public Iterable<Integer> pre() {
        return this.pre;
    }

    public Iterable<Integer> post() {
        return this.post;
    }

    public static void main(String[] args) {
        Graph g = new Graph("src/graph/g.txt");
        GraphDFS dfs = new GraphDFS(g);
        System.out.println(dfs.pre());
        System.out.println(dfs.post());
    }


}
