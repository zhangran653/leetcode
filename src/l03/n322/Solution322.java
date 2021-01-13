package l03.n322;//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。 
//
// 示例 1: 
//
// 输入: coins = [1, 2, 5], amount = 11
//输出: 3 
//解释: 11 = 5 + 5 + 1 
//
// 示例 2: 
//
// 输入: coins = [2], amount = 3
//输出: -1 
//
// 说明: 
//你可以认为每种硬币的数量是无限的。 
// Related Topics 动态规划


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 动态规划
 */
class Solution322 {
    /**
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
        }
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != amount + 1) {
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    /**
     * 直接递归超时
     *
     * @param coins
     * @param amount
     * @return
     */
    private int res;


    public int coinChange1(int[] coins, int amount) {
        res = amount + 1;
        _coinChange(0, amount, coins);
        return res == amount + 1 ? -1 : res;
    }

    private void _coinChange(int count, int amount, int[] coins) {
        if (amount < 0) {
            return;
        }
        if (amount == 0) {
            res = Math.min(res, count);
            return;
        }
        for (int coin : coins) {
            if (amount - coin < 0) {
                continue;
            }
            _coinChange(count + 1, amount - coin, coins);
        }
    }

    /**
     * 递归加记忆化搜索
     *
     * @param coins
     * @param amount
     * @return
     */
    // memo[n]表示换取n最少的硬币数量
    private int[] memo;

    public int coinChange2(int[] coins, int amount) {
        memo = new int[amount + 1];
        return _coinChange1(amount, coins);
    }

    private int _coinChange1(int amount, int[] coins) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (memo[amount] != 0) {
            return memo[amount];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = _coinChange1(amount - coin, coins);
            if (res >= 0 && res < min) {
                min = res + 1;
            }
        }
        memo[amount] = (min == Integer.MAX_VALUE ? -1 : min);
        return memo[amount];
    }

}
//leetcode submit region end(Prohibit modification and deletion)
