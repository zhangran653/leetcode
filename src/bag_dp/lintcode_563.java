package bag_dp;

/**
 * 0-1背包问题
 * 563. 背包问题 V
 * 给出 n 个物品, 以及一个数组, nums[i] 代表第i个物品的大小, 保证大小均为正数, 正整数 target 表示背包的大小, 找到能填满背包的方案数。
 * 每一个物品只能使用一次
 *
 * @author zhangran
 * @since 2020-04-18
 **/
public class lintcode_563 {
    public int backPackV(int[] nums, int target) {
        // write your code here
        int n = nums.length;
        // dp[i][j]表示前i个数字有多少种方式拼出数字j
        int[][] dp = new int[n + 1][target + 1];
        // 0个物品拼出0有1种方式
        dp[0][0] = 1;
        // 初始化

        for (int i = 1; i <= n; i++) {
            // 拼出几
            for (int j = 0; j <= target; j++) {
                // 不放
                dp[i][j] = dp[i - 1][j];
                //放
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[n][target];
    }
}
