package graph;

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

    public static void main(String[] args) {
        Graph g = new Graph("src/graph/g.txt");
        CC dfs = new CC(g);
        System.out.println(dfs.count());
    }


}
