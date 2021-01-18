package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SingleSourcePath {
    private Graph graph;
    private int s;
    private int[] visited;
    private int[] pre;


    public SingleSourcePath(Graph graph, int s) {
        graph.validateVertex(s);
        this.graph = graph;
        this.s = s;
        visited = new int[graph.V()];
        pre = new int[graph.V()];
        Arrays.fill(pre, -1);
        dfs(s, s);
    }

    private boolean visited(int v) {
        return visited[v] != 0;
    }


    private void dfs(int v, int parent) {
        visited[v] = 1;
        pre[v] = parent;
        for (int w : graph.adj(v)) {
            if (!visited(w)) {
                dfs(w, v);
            }
        }
    }

    public boolean isConnected(int t) {
        graph.validateVertex(t);
        return visited[t] == 1;
    }

    public Iterable<Integer> path(int t) {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected(t)) {
            return res;
        }
        int cur = t;
        while (cur != this.s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;

    }

    public static void main(String[] args) {
        Graph g = new Graph("src/graph/g3.txt");
        SingleSourcePath s = new SingleSourcePath(g, 0);
        System.out.println(s.path(6));
    }


}
