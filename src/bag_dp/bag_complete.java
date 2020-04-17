package bag_dp;

/**
 * 完全背包
 *
 * @author zhangran
 * @since 2020-04-17
 **/
public class bag_complete {


    public int backPackIII(int[] A, int[] V, int m) {
        // write your code here
        int n = A.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if (j < A[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 可重复，dp[i][j - weight[i - 1]] + value[i - 1] 表示再取一件
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        return dp[n][m];
    }

    /**
     * 优化
     *
     * @param A
     * @param V
     * @param m
     * @return
     */
    public int backPackIII2(int[] A, int[] V, int m) {
        // write your code here
        int n = A.length;
        int[] dp = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = A[i - 1]; j <= m; j++) {
                dp[j] = Math.max(dp[j], dp[j - A[i - 1]] + V[i - 1]);
            }
        }
        return dp[m];
    }

}
