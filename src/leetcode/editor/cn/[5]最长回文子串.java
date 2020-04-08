package leetcode.editor.cn;//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution5 {
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        int len = s.length();
        // 1.定义dp[i][j] 表示Sij是否为回文
        boolean[][] dp = new boolean[len][len];

        // 2.初始化 dp[i][i]为true
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        int start = 0;
        int maxLen = 1;
        //3.状态转移
        // dp[i][j] = (dp[i+1][j-1] && Si == Sj)
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    //边界条件是：表达式 [i + 1, j - 1] 不构成区间，即长度严格小于 2，
                    // 即 j - 1 - (i + 1) + 1 < 2 ，整理得 j - i < 3。
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }

                // 更新start，max
                // 只要 dp[i][j] == true 成立，就表示子串 s[i, j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j]) {
                    int curLen = j - i + 1;
                    if (curLen > maxLen) {
                        maxLen = curLen;
                        start = i;
                    }
                }
            }
        }

        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        String e = new Solution5().longestPalindrome("ccc");
        System.out.println(e);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
