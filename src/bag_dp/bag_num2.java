package bag_dp;

/**
 * 求方案数
 * 给出 n 个物品, 以及一个数组, nums[i]代表第i个物品的大小, 保证大小均为正数并且没有重复, 正整数 target 表示背包的大小, 找到能填满背包的方案数。
 * 每一个物品可以使用无数次
 *
 * @author zhangran
 * @since 2020-04-17
 **/
public class bag_num2 {


    public int backPackIV(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - nums[i - 1]];
                }
            }
        }
        return dp[n][target];

    }

}
