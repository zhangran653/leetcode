package leetcode.editor.cn;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhangran
 * @since 2020-05-24
 **/
public class ClimbStairs {
    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 注意：给定 n 是一个正整数。
     */
    public int climbStairs(int n) {
        // TODO leetcode 70题，已通过
        if (n == 1) {
            return 1;
        }
        // dp[i] = dp[i-1] + dp[i-2]
        int i1 = 1, i2 = 2;
        for (int i = 3; i <= n; i++) {
            int temp = i1 + i2;
            i1 = i2;
            i2 = temp;
        }
        return i2;
    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次爬的步数有一个step数组表示，你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 注意：给定 n 是一个正整数。int[] steps 非空数组
     */
    public int climbStairs2(int n, int[] steps) {
        // TODO 使用这种做法做leetcode的70题，即 return climbStairs2(int n, new int[]{1,2}) 也通过
        // 递推方程 dp[i] = (dp[i-steps[0]] + dp[i-steps[1]] + ... + dp[i-steps[m-1]),m为steps长度
        // 边界问题，是否一定有解需考虑
        List<Integer> validSteps = new LinkedList<>();
        int[] dp = new int[n + 1];
        // 1.步数大于楼梯数排除
        for (int step : steps) {
            if (step <= n) {
                validSteps.add(step);
            }
        }
        if (validSteps.isEmpty()) {
            return 0;
        }
        // 步数从小到大排序
        validSteps.sort(Comparator.naturalOrder());
        //2. 初始化，从最小步数到最大步数都可以初始化
        dp[validSteps.get(0)] = 1;
        for (int i = validSteps.get(0) + 1; i <= validSteps.get(validSteps.size() - 1); i++) {
            for (int j = 0; j < validSteps.size() && validSteps.get(j) <= i; j++) {
                // dp[i-steps[j]] 为用步数step[j]走到i之前的步数，+1即为dp[i]，如果当前步数正好是i，则还需要+1
                dp[i] += (i == validSteps.get(j) ? 1 : 0) + dp[i - validSteps.get(j)];
            }
        }
        //3. 递推
        // 从最大步数之后开始，因为之前的已经初始化过了
        for (int i = validSteps.get(validSteps.size() - 1) + 1; i <= n; i++) {
            for (int step : validSteps) {
                // 递推方程 dp[i] = (dp[i-steps[0]] + dp[i-steps[1]] + ... + dp[i-steps[m-1])
                dp[i] += dp[i - step];
            }
        }
        return dp[n];
    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次爬的步数有一个step数组表示，并且已经走过的步数不能重复走。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 注意：给定 n 是一个正整数。int[] steps 非空数组
     */
    public int climbStairs3(int n, int[] steps) {

        // dp[i][j] 表示最后一步走了j步到i层台阶
        int[][] dp = new int[n + 1][steps.length];

        //TODO ??

//        for (int i = start; i <= n; i++) {
//            for (int step : steps) {
//                for (int j=0;j < steps.length;j++){
//                    最后一步是走了j步上来的，那么之前就不能走j步
//                    dp[i][steps[j]] += dp[i - steps[j]][?];
//                }
//            }
//        }

        return 0;
    }

    public static void main(String[] args) {
        int r = new ClimbStairs().climbStairs2(3, new int[]{1, 2});
        System.out.println(r);
    }

}
