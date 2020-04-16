package bag_dp;

/**
 * 求方案数
 * 给出 n 个物品, 以及一个数组, nums[i] 代表第i个物品的大小, 保证大小均为正数, 正整数 target 表示背包的大小, 找到能填满背包的方案数。
 * 每一个物品只能使用一次
 * <p>
 * 可用滚动数组降空间至一维数组，因为用到了前面保存的值，如果要降维到一维数组，计算顺序要从后往前，这样不会覆盖前面的值。
 *
 * @author zhangran
 * @since 2020-04-17
 **/
public class bag_num {

    /**
     * 分析】需要求出有多少种组合能组合成target，对于最后一个物品，有放和不放两种选择。
     * <p>
     * 第一种：使用前n-1个物品拼出target
     * 第二种：前n-1个物品能拼出target - nums[i]，再加上nums[i]，拼出target
     * 拼出target的方式 = 不放+放,即dp[i][j] = dp[i-1][j] + dp[i-1][j - nums[i-1]]
     * 如果知道有多少种方式拼出0、1、2…对于有多少种方式拼出target也就知道答案了。
     * ———————————————
     * 原文链接：https://blog.csdn.net/weixin_44424668/java/article/details/104017370
     *
     * @param nums
     * @param target
     * @return
     */
    public int backPackV(int[] nums, int target) {
        int n = nums.length;
        // dp[i][j]表示前i个数字有多少种方式拼出数字j
        int[][] dp = new int[n + 1][target + 1];
        // 0个物品拼出0有1种方式
        dp[0][0] = 1;
        // 初始化
        for (int t = 1; t <= target; t++) {
            dp[0][t] = 0;
        }
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

    /**
     * 优化
     *
     * @param nums
     * @param target
     * @return
     */
    public int backPackV2(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[target + 1]; //和总称重有关
        //init：相当于dp[0][0] = 1
        dp[0] = 1;
        //init：dp[0][1] = dp[0][2] = ... = 0
        for (int i = 1; i <= target; i++) {
            dp[i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            //reverse
            for (int j = target; j >= 0; j--) {
                if (j >= nums[i - 1]) {
                    //old + old ==> new old1 = dp[j],old2 = dp[j - nums[i - 1]],new就是直接覆盖
                    dp[j] += dp[j - nums[i - 1]];
                }
            }
        }
        return dp[target];
    }

}
