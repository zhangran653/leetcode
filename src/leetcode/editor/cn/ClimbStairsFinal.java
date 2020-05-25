package leetcode.editor.cn;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 动态规划管中窥豹 ————
 * 爬楼梯，换零钱，装背包
 *
 * @author zhangran
 * @since 2020-05-25
 **/
public class ClimbStairsFinal {
    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 注意：给定 n 是一个正整数。
     * </p>
     */
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 进一步泛化问题，如果不是1，2，而是任意的台阶数呢？
     * 如果是，1，2
     * 递推方程 dp[i] = dp[i-1] + dp[i-2]
     * 如果是 1，2，3呢
     * dp[i] = dp[i-1]+dp[i-2]+dp[i-3]
     * <p>
     * 归纳为：
     * dp[i] = dp[i] + dp[i-j] , j=1,2,3 （dp[i] 初始化为0）
     */
    public int climbStairs2(int n, int[] steps) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int step : steps) {
                if (i < step) {
                    // 台阶小于步数
                    continue;
                }
                dp[i] += dp[i - step];
            }
        }
        return dp[n];
    }

    /**
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     * <p>
     * 输入: amount = 5, coins = [1, 2, 5]
     * 输出: 4
     * 解释: 有四种方式可以凑成总金额:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     * <p>
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        // 子问题：dp[i] = sum(dp[i-j]) ,j = 1,2,3...
        // 凑成总金额i的硬币组合数等于凑成 i-1,i-2,i-3...的子问题之和,和爬楼梯的状态转移方程一样

        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin > i) {
                    continue;
                }
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    /**
     * 然而，这样做却不对！
     * 哪里出错了呢。换零钱和爬楼梯的一个重要区别，是一个是组合数，一个是排列数
     * 比如1，2和2，1在换零钱种是一种方法，在爬楼梯中是2种。
     * <p>
     * 根本原因还是dp方程出了问题。
     * <p>
     * 正确的定义应该是：
     * dp[k][i] 表示前k个硬币凑到金额为i的组合数。
     * dp[k][i] = dp[k-1][i] + dp[k][i-coin[k]]. 表示前k个硬币凑到i金额有2种方式，一种是前k-1个硬币已经凑到i了，
     * 另一种是前面已经凑到i-coin[k]了，现在就差第k个硬币了
     */
    public int changeRight(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        // 初始化
        dp[0][0] = 1;
        for (int k = 1; k <= coins.length; k++) {
            for (int i = 0; i <= amount; i++) {
                // 金额大于等于当前coin，那么有2种方式，注意索引为k-1
                if (i >= coins[k - 1]) {
                    dp[k][i] = dp[k - 1][i] + dp[k][i - coins[k - 1]];
                } else {
                    dp[k][i] = dp[k - 1][i];
                }
            }
        }
        return dp[coins.length][amount];
    }

    /**
     * 如果在这里，交换内外循环会怎么样？
     * 答案是不会影响结果
     */
    public int changeRight2(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        // 初始化
        dp[0][0] = 1;
        for (int i = 0; i <= amount; i++) {
            for (int k = 1; k <= coins.length; k++) {
                // 金额大于等于当前coin，那么有2种方式，注意索引为k-1
                if (i >= coins[k - 1]) {
                    dp[k][i] = dp[k - 1][i] + dp[k][i - coins[k - 1]];
                } else {
                    dp[k][i] = dp[k - 1][i];
                }
            }
        }
        return dp[coins.length][amount];
    }

    /**
     * 第一个爬楼梯，可以将一维数组降维，变成一个点，即斐波那契数列就可以
     * 换零钱也可以降维，仅用一维数组表示
     * <p>
     * 重新定义子问题，对于硬币从0到k，在<I>选择</I>第k个硬币的情况下，当前金额的组合数
     * dp[i] 表示对第k个硬币能凑的组合数
     * dp[i] = dp[i] + dp[i-coin[k]]
     */
    public int changeRight3(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        // 初始化
        dp[0] = 1;
        // 枚举硬币
        for (int coin : coins) {
            // 枚举金额
            for (int i = 1; i <= amount; i++) {
                // coin不能大于amount
                if (i < coin) {
                    continue;
                }
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    /**
     * 现在再来看看它和爬楼梯的区别,就是循环的嵌套上。
     * <p>
     * 把问题抽象成，给定一个数组，求组成数 x 的排列数和组合数。那么循环嵌套的差异，一个数组合数，一个是排列数。
     * <p>
     * 这两层循环的显然不能交换。如果换了，子问题的定义就变了。原循环结构对应的子问题是，对于楼梯数i, 我们的爬楼梯方案。
     * 如果交换，子问题就是固定爬楼梯的顺序，我们爬楼梯的方案。也就是第一种循环下，对于楼梯3，你可以先2再1，或者先1再2，
     * 但是对于第二种循环则暗中定好了使用的次数，不允许之后的层次使用之前的步数，定好了顺序，自然就只有1，2这一种情况了。
     * <p>
     * 总之，如果跨越的步数放在外层，台阶数放里层相当于规定了先爬小的台阶数，再爬大的台阶数，求的是组合数。
     * 台阶数放外层则是可以改变跨越步数的顺序，三阶可以是先爬一阶再爬两阶，也可以是先爬两阶再爬一阶，求的是排列数
     */
    public int climbStairsCopy(int n, int[] steps) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int step : steps) {
                if (i < step) {
                    // 台阶小于步数
                    continue;
                }
                dp[i] += dp[i - step];
            }
        }
        return dp[n];
    }

    /**
     * 再编一个题
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。
     * 假设每一种面额的硬币不再是无限个，而是只有一个
     * 哎，为何总是折磨自己~
     * <p>
     * 1. 定义dp[k][i] 表示前k个硬币金额为i的组合数。
     * 2. 子问题 对于一个硬币，有拿和不拿2种选择，对于不拿，dp[k][i] = dp[k-1][i],对于拿，dp[k][i] = dp[k-1][i-coin[k]]
     * 即                            dp[k][i] = dp[k-1][i] + dp[k-1][i-coin[k]]
     * 再来对比一下，不限数量的子问题 dp[k][i] = dp[k-1][i] + dp[k][i-coin[k]].
     * 看看怎么这么像，只有拿的时候，一个是dp[k-1],另一个是dp[k]
     */
    public int changeRight4(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for (int k = 1; k <= coins.length; k++) {
            for (int i = 0; i <= amount; i++) {
                // 不拿
                dp[k][i] = dp[k - 1][i];
                // 拿
                if (i >= coins[k - 1]) {
                    dp[k][i] += dp[k - 1][i - coins[k - 1]];
                }
            }
        }
        return dp[coins.length][amount];
    }

    /**
     * 压缩一维。实际上这个问题就是0-1背包问题的方案数问题。上面的不限个数就是完全背包问题的方案数问题。
     * 往经典框架上面套，理解可能会更简单
     */
    public int changeRight5(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int k = 1; k <= coins.length; k++) {
            // 一定要倒叙
            for (int i = amount; i >= coins[k - 1]; i--) {
                dp[i] += dp[i - coins[k - 1]];
            }
        }
        return dp[amount];
    }

    /**
     * 最后回到最初的问题
     * <p>
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次爬的步数有一个step数组表示，并且已经走过的步数不能重复走。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 注意：给定 n 是一个正整数。int[] steps 非空数组
     */
    public int climbStairs3(int n, int[] steps) {
        /**
         *  dp[i][k] 表示最后一步通过走k步到了i级台阶，
         *  那么子问题就是
         *  dp[i][k] = sum(dp[i-k][j]) ，j = for step in [steps_without_taken]
         *
         *  实际写出来，相当麻烦
         *
         */
        Arrays.sort(steps);

        // 第二个维度最大为 min(n, max(steps))
        int maxStep = steps[steps.length - 1];
        int K = Math.min(n, maxStep) + 1;
        int[][] dp = new int[n + 1][K];
        // 初始化
        dp[0][0] = 1;
        System.out.println("steps:" + Arrays.toString(steps));
        int[] copy = new int[steps.length + 1];
        System.arraycopy(steps, 0, copy, 1, steps.length);
        copy[0] = 0;
        for (int i = 1; i <= n; i++) {
            System.out.println();
            System.out.println("level:" + i);
            System.out.println("before" + Arrays.deepToString(dp));
            for (int step : copy) {
                Set<Integer> levelCache = new HashSet<>();
                if (i < step) {
                    // 台阶小于步数
                    continue;
                }
                // 这里0步也要考虑，所以加上0
                for (int j : copy) {
                    if (levelCache.contains(j)) {
                        continue;
                    }
                    if (i - step < j) {
                        continue;
                    }
                    if (j != 0) {
                        levelCache.add(j);
                    }

                    // TODO 递推公式这里有问题，dp[i][k] = sum(dp[i-k][j]) ，j = for step in [steps_without_taken]怎么表示？
                    //  dp[i][step] += dp[i - step-j][j]; ?
                    dp[i][step] += dp[i - step][j];
                }
            }
            System.out.println("after" + Arrays.deepToString(dp));
            System.out.println();
        }

        int count = 0;
        for (int i = 0; i < K; i++) {
            count += dp[n][i];
        }
        return count;
    }

    public static void main(String[] args) {
        int r1 = new ClimbStairsFinal().climbStairs3(1, new int[]{1, 2});
        int r2 = new ClimbStairsFinal().climbStairs3(2, new int[]{1, 2});
        int r3 = new ClimbStairsFinal().climbStairs3(3, new int[]{1, 2});
        int r4 = new ClimbStairsFinal().climbStairs3(4, new int[]{1, 2, 3});
        int r5 = new ClimbStairsFinal().climbStairs3(6, new int[]{1, 2, 3});
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        System.out.println(r4);
        System.out.println(r5);

    }
}
