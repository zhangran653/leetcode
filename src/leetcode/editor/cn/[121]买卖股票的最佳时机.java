package leetcode.editor.cn;//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
//
// 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。 
//
// 注意：你不能在买入股票前卖出股票。 
//
// 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
//输出: 5
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
// 
//
// 示例 2: 
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
// 
// Related Topics 数组 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution121 {
    /**
     * 动态规划
     * 区间和可以转换成求差的问题，求差问题，也可以转换成区间和的问题
     * <p>
     * dp[i] 表示前 i 天的最大利润，因为我们始终要使利润最大化，则：
     * <p>
     * dp[i] = max(dp[i-1], prices[i]-minprice)
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        // 1. 定义状态,dp[i] 表示前 i 天的最大利润
        int[] dp = new int[prices.length];

        //2. 初始化min
        int min = prices[0];

        //3.状态转移方程
        // dp[i] = max(dp[i-1], prices[i]-minprice)
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            dp[i] = Math.max(dp[i - 1], prices[i] - min);
        }
        return dp[prices.length - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
