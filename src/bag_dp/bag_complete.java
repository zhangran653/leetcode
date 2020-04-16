package bag_dp;

/**
 * @author zhangran
 * @since 2020-04-17
 **/
public class bag_complete {
    public int completePack(int V, int N, int[] weight, int[] value) {
        //初始化动态规划数组
        int[][] dp = new int[N + 1][V + 1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if (weight[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 可重复，dp[i][j - weight[i - 1]] + value[i - 1] 表示再取一件
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        //则容量为V的背包能够装入物品的最大值为
        return dp[N][V];
    }

    public int completePack1(int V, int N, int[] weight, int[] value) {
        //初始化动态规划数组
        int[] dp = new int[V + 1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for (int i = 0; i < N; i++) {
            for (int j = weight[i]; j <= V; j++) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        return dp[V];
    }

    /**
     * 背包问题 I —— 0-1背包无价值
     */
    public int zeroOnePack(int V, int N, int[] weight, int[] value) {

        //初始化动态规划数组
        int[][] dp = new int[N + 1][V + 1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if (weight[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        //则容量为V的背包能够装入物品的最大值为
        return dp[N][V];
    }
}
