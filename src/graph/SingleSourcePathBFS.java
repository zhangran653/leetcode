package graph;

import java.util.*;

public class SingleSourcePathBFS {
    private Graph graph;
    private int[] visited;
    private int[] pre;
    private int s;
    private ArrayList<Integer> order = new ArrayList<>();

    public SingleSourcePathBFS(Graph graph, int s) {
        visited = new int[graph.V()];
        pre = new int[graph.V()];
        this.graph = graph;
        this.s = s;
        Arrays.fill(pre, -1);
        graph.validateVertex(s);
        bfs(s);
    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = 1;
        pre[s] = s;

        while (!queue.isEmpty()) {
            int v = queue.remove();
            order.add(v);
            for (int w : graph.adj(v)) {
                if (visited[w] != 1) {
                    queue.add(w);
                    visited[w] = 1;
                    pre[w] = v;
                }
            }
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isConnectedTo(int t) {
        return visited[t] == 1;
    }

    public Iterable<Integer> path(int t) {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t)) {
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
        SingleSourcePathBFS s = new SingleSourcePathBFS(g, 0);
        System.out.println(s.path(4));
    }

}
