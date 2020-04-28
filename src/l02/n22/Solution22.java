package l02.n22;//给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
//
// 例如，给出 n = 3，生成结果为： 
//
// [
//  "((()))",
//  "(()())",
//  "(())()",
//  "()(())",
//  "()()()"
//]
// 
// Related Topics 字符串 回溯算法


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution22 {
    public List<String> generateParenthesis(int n) {
        List<String> s = new ArrayList<>();
        _generate(s, 0, 0, n, "");
        return s;
    }

    private void _generate(List<String> res, int left, int right, int n, String s) {
        if (left == n && right == n) {
            res.add(s);
            return;
        }
        if (left < n) {
            _generate(res, left + 1, right, n, s + "(");
        }
        if (right < n && right < left) {
            _generate(res, left, right + 1, n, s + ")");
        }
    }


}
//leetcode submit region end(Prohibit modification and deletion)
