package graph;

import java.util.Arrays;

public class BiPartitionDetection {

    private Graph graph;
    private int[] visited;
    private int[] colors;
    private boolean isBipartite = true;

    public BiPartitionDetection(Graph graph) {
        this.graph = graph;
        visited = new int[graph.V()];
        colors = new int[graph.V()];
        Arrays.fill(colors, -1);
        for (int v = 0; v < graph.V(); v++) {
            if (!visited(v)) {
                if (!dfs(v, 0)) {
                    isBipartite = false;
                    break;
                }
            }
        }
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    private boolean visited(int v) {
        return visited[v] == 1;
    }


    private boolean dfs(int v, int color) {
        visited[v] = 1;
        colors[v] = color;
        for (int w : graph.adj(v)) {
            if (!visited(w)) {
                if (!dfs(w, 1 - color)) {
                    return false;
                } else if (colors[w] == colors[v]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Graph g1 = new Graph("src/graph/g.txt");
        BiPartitionDetection cd1 = new BiPartitionDetection(g1);
        System.out.println(cd1.isBipartite());
    }

}
