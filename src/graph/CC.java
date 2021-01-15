package graph;

import java.util.ArrayList;
import java.util.Arrays;

public class CC {
    private Graph graph;
    private int[] visited;
    private int cccount;


    public CC(Graph graph) {
        this.graph = graph;
        visited = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            visited[i] = -1;
        }
        for (int v = 0; v < graph.V(); v++) {
            if (!visited(v)) {
                dfs(v, cccount);
                cccount++;
            }
        }
    }

    private boolean visited(int v) {
        return visited[v] != -1;
    }


    private void dfs(int v, int ccid) {
        visited[v] = ccid;

        for (int w : graph.adj(v)) {
            if (!visited(w)) {
                dfs(w, ccid);
            }
        }
    }

    public int count() {
        return cccount;
    }

    public boolean isConnected(int v, int w) {
        graph.validateVertex(v);
        graph.validateVertex(w);
        return visited[v] == visited[w];
    }

    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] components = new ArrayList[cccount];
        for (int c = 0; c < cccount; c++) {
            components[c] = new ArrayList<>();
        }
        for (int v = 0; v < graph.V(); v++) {
            components[visited[v]].add(v);
        }
        return components;
    }

    public static void main(String[] args) {
        Graph g = new Graph("src/graph/g.txt");
        CC dfs = new CC(g);
        System.out.println(dfs.count());
        System.out.println(dfs.isConnected(0, 2));
        System.out.println(Arrays.toString(dfs.components()));
    }


}
