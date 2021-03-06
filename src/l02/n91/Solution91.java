package l02.n91;//一条包含字母 A-Z 的消息通过以下方式进行了编码：
//
// 'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 示例 1: 
//
// 输入: "12"
//输出: 2
//解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2: 
//
// 输入: "226"
//输出: 3
//解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
// Related Topics 字符串 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution91 {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            char cur = s.charAt(i - 1);
            char pre = s.charAt(i - 2);
            // ... x y
            //1. x==0,y==0
            if (pre == '0' && cur == '0') {
                return 0;
            }
            //2. 只有x==0
            else if (pre == '0') {
                dp[i] = dp[i - 1];
            }
            //3. 只有y==0
            else if (cur == '0') {
                if (pre == '1' || pre == '2') {
                    dp[i] = dp[i - 2];
                } else {
                    return 0;
                }
            }
            //4. xy>26
            else if (pre > '2' || (pre == '2' && cur > '6')) {
                dp[i] = dp[i - 1];
            }
            //5. xy<=26
            else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[s.length()];

    }

    public static void main(String[] args) {
        int s = new Solution91().numDecodings("12");
        System.out.println(s);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
