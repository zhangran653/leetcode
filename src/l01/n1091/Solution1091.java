package l01.n1091;//在一个 N × N 的方形网格中，每个单元格有两种状态：空（0）或者阻塞（1）。
//
// 一条从左上角到右下角、长度为 k 的畅通路径，由满足下述条件的单元格 C_1, C_2, ..., C_k 组成： 
//
// 
// 相邻单元格 C_i 和 C_{i+1} 在八个方向之一上连通（此时，C_i 和 C_{i+1} 不同且共享边或角） 
// C_1 位于 (0, 0)（即，值为 grid[0][0]） 
// C_k 位于 (N-1, N-1)（即，值为 grid[N-1][N-1]） 
// 如果 C_i 位于 (r, c)，则 grid[r][c] 为空（即，grid[r][c] == 0） 
// 
//
// 返回这条从左上角到右下角的最短畅通路径的长度。如果不存在这样的路径，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 输入：[[0,1],[1,0]]
//
//输出：2
//
// 
//
// 示例 2： 
//
// 输入：[[0,0,0],[1,1,0],[1,1,0]]
//
//输出：4
//
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length == grid[0].length <= 100 
// grid[i][j] 为 0 或 1 
// 
// Related Topics 广度优先搜索


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1091 {


    /**
     * 双向BFS，通过
     *
     * @param grid
     * @return
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int m = grid.length;
        int n = grid[0].length;

        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }
        if (grid.length == 1 && grid[0].length == 1 && grid[0][0] == 0) {
            return 1;
        }

        Deque<int[]> q1 = new ArrayDeque<>();
        int[][] v1 = new int[m][n];
        q1.addLast(new int[]{0, 0});
        v1[0][0] = 1;

        Deque<int[]> q2 = new ArrayDeque<>();
        int[][] v2 = new int[m][n];
        q2.addLast(new int[]{m - 1, n - 1});
        v2[m - 1][n - 1] = 1;

        int count = 1;
        int[][] moves = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        while (!q1.isEmpty() && !q2.isEmpty()) {
            count++;
            if (q1.size() > q2.size()) {
                Deque<int[]> temp = q1;
                q1 = q2;
                q2 = temp;

                int[][] v = v1;
                v1 = v2;
                v2 = v;
            }
            int size = q1.size();
            while (size-- > 0) {
                int[] loc = q1.pollFirst();
                for (int[] move : moves) {
                    int newx = loc[0] + move[0];
                    int newy = loc[1] + move[1];
                    if (!canMove(grid, newx, newy)) {
                        continue;
                    }
                    if (v1[newx][newy] == 1) {
                        continue;
                    }
                    if (v2[newx][newy] == 1) {
                        return count;
                    }
                    q1.addLast(new int[]{newx, newy});
                    v1[newx][newy] = 1;
                }
            }
        }
        return -1;
    }

    int m;
    int n;

    class Node implements Comparable<Node> {
        public int x;
        public int y;
        public int f;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            int distance = Math.max(n - 1 - x, n - 1 - y);
            this.f = distance + step;
        }

        @Override
        public int compareTo(Node o) {
            return this.f - o.f;
        }


    }

    /**
     * A* search
     *
     * @param grid
     * @return
     */
    public int shortestPathBinaryMatrix1(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        m = grid.length;
        n = grid[0].length;

        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }
        if (grid.length == 1 && grid[0].length == 1 && grid[0][0] == 0) {
            return 1;
        }

        Queue<Node> q1 = new PriorityQueue<>();
        q1.offer(new Node(0, 0, 1));
        grid[0][0] = 1;


        int[][] moves = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        while (!q1.isEmpty()) {
            Node node = q1.poll();
            int step = grid[node.x][node.y];
            for (int[] d : moves) {
                int x = node.x + d[0];
                int y = node.y + d[1];
                if (x == n - 1 && y == n - 1) return step + 1;
                if (x < 0 || x >= n || y < 0 || y >= n) continue;
                if (grid[x][y] != 0 && grid[x][y] <= step + 1) continue;
                Node next = new Node(x, y, grid[x][y] = step + 1);
                q1.offer(next);
            }
        }


        return -1;
    }

    private boolean canMove(int[][] grid, int x, int y) {
        return !(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 1);
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int l = new Solution1091().shortestPathBinaryMatrix1(grid);
        System.out.println(l);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
