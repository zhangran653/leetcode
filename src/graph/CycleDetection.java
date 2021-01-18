package graph;

public class CycleDetection {

    private Graph graph;
    private int[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph graph) {
        this.graph = graph;
        visited = new int[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            if (!visited(v)) {
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    private boolean visited(int v) {
        return visited[v] == 1;
    }


    private boolean dfs(int v, int parent) {
        visited[v] = 1;

        for (int w : graph.adj(v)) {
            if (!visited(w)) {
                if (dfs(w, v)) {
                    return true;
                }
            } else if (w != parent) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }


    public static void main(String[] args) {
        Graph g1 = new Graph("src/graph/g.txt");
        CycleDetection cd1 = new CycleDetection(g1);
        System.out.println(cd1.hasCycle());

        Graph g2 = new Graph("src/graph/g2.txt");
        CycleDetection cd2 = new CycleDetection(g2);
    }
}
