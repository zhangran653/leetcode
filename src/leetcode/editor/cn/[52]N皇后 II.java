package leetcode.editor.cn;//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回 n 皇后不同的解决方案的数量。 
//
// 示例: 
//
// 输入: 4
//输出: 2
//解释: 4 皇后问题存在如下两个不同的解法。
//[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
// 
//
// 
//
// 提示： 
//
// 
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或七步
//，可进可退。（引用自 百度百科 - 皇后 ） 
// 
// Related Topics 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution52 {
    private int res;

    public int totalNQueens(int n) {
        // queue[n] = i表示第n行第i列为皇后
        int[] queue = new int[n];
        dfs(0, queue, n);
        return res;
    }

    private void dfs(int i, int[] queue, int n) {
        if (i == n) {
            res++;
            return;
        }
        for (int j = 0; j < n; j++) {
            queue[i] = j;
            if (!check(queue, i)) {
                continue;
            }
            dfs(i + 1, queue, n);
        }
    }

    private boolean check(int[] queue, int i) {
        for (int j = 0; j < i; j++) {
            if (queue[i] == queue[j] || Math.abs(i - j) == Math.abs(queue[i] - queue[j])) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
