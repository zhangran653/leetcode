package l01.n51;//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 示例: 
//
// 输入: 4
//输出: [
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
// Related Topics 回溯算法


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution51 {

    private List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // queen[n] = i 表示第n行第i列为皇后
        int[] queen = new int[n];
        backtrack(0, queen, n);
        return result;
    }

    private void backtrack(int curRow, int[] queen, int n) {
        if (curRow == n) {
            result.add(printBoard(queen));
            return;
        }
        for (int col = 0; col < n; col++) {
            queen[curRow] = col;
            if (check(queen, n, curRow)) {
                continue;
            }
            backtrack(curRow + 1, queen, n);
        }
    }

    private boolean check(int[] queen, int max, int nth) {
        for (int i = 0; i < nth; i++) {
            //说明冲突
            if (Math.abs(nth - i) == Math.abs(queen[nth] - queen[i]) || (queen[nth] == queen[i])) {
                return true;
            }
        }
        return false;
    }


    private List<String> printBoard(int[] queen) {
        List<String> result = new ArrayList<>();
        for (int r = 0; r < queen.length; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < queen.length; c++) {
                sb.append(queen[r] == c ? "Q" : ".");
            }
            result.add(sb.toString());
        }
        return result;
    }


}


//leetcode submit region end(Prohibit modification and deletion)
