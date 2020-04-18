package bag_dp;

/**
 * 给出 n 个物品, 以及一个数组, nums[i]代表第i个物品的大小, 保证大小均为正数并且没有重复, 正整数 target 表示背包的大小, 找到能填满背包的方案数。
 * 每一个物品可以使用无数次
 * 完全背包问题
 * <p>
 * leetcode 518 零钱兑换一样
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 *
 * @author zhangran
 * @since 2020-04-18
 **/
public class lintcode_562 {
    public int backPackIV(int[] nums, int target) {
        int n = nums.length;
        int m = target;
        // 定义dp[i][j] i个物品组成j的方案个数
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i][j - nums[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][m];
    }

    public int backPackIV1(int[] nums, int m) {
        // write your code here
        int n = nums.length;
        int[] dp = new int[m + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = nums[i - 1]; j <= m; j++) {

                dp[j] = dp[j] + dp[j - nums[i - 1]];

            }
        }
        return dp[m];
    }
}
