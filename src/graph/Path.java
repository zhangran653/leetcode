package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Path {
    private Graph graph;
    private int s;
    private int t;
    private int[] visited;
    private int[] pre;


    public Path(Graph graph, int s, int t) {
        graph.validateVertex(s);
        graph.validateVertex(t);

        this.graph = graph;
        this.s = s;
        this.t = t;
        visited = new int[graph.V()];
        pre = new int[graph.V()];
        Arrays.fill(pre, -1);
        dfs(s, s);
    }

    private boolean visited(int v) {
        return visited[v] != 0;
    }


    private boolean dfs(int v, int parent) {
        visited[v] = 1;
        pre[v] = parent;
        if (v == t) {
            return true;
        }
        for (int w : graph.adj(v)) {
            if (!visited(w)) {
                if (dfs(w, v)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isConnected() {
        return visited[t] == 1;
    }

    public Iterable<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected()) {
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
        Graph g = new Graph("src/graph/g.txt");
        Path s = new Path(g, 0, 1);
        System.out.println(Arrays.toString(s.visited));
        System.out.println(s.path());
    }


}
