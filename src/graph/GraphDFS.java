package graph;

import java.util.ArrayList;
import java.util.List;

public class GraphDFS {
    private List<Integer> pre = new ArrayList<>();
    private List<Integer> post = new ArrayList<>();
    private Graph graph;
    private int[] visited;

    public GraphDFS(Graph graph) {
        this.graph = graph;
        visited = new int[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            if (!visited(v)) {
                dfs(v);
            }
        }
    }

    private boolean visited(int v) {
        return visited[v] == 1;
    }


    private void dfs(int v) {
        visited[v] = 1;
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
