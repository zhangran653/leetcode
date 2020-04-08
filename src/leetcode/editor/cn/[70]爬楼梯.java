package leetcode.editor.cn;//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 1. 递归+缓存
 * 不加缓存时间复杂度O(2^n) ,树的深度n，空间复杂度O(n)
 * 加缓存时间复杂度O(n),
 */
class Solution70 {
    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        return climb_stairs(0, n, memo);
    }

    /**
     * @param i    当前的级数
     * @param n    目标的级数
     * @param memo 缓存
     * @return
     */
    public int climb_stairs(int i, int n, int[] memo) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_stairs(i + 1, n, memo) + climb_stairs(i + 2, n, memo);
        return memo[i];
    }
}

/**
 * 斐波那契数列
 * 时间复杂度O(n) 空间复杂度O(1)
 */
class Solution70_1 {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int p = 1;
        int q = 2;
        int r = 0;
        for (int i = 0; i < n - 2; i++) {
            r = p + q;
            p = q;
            q = r;
        }
        return r;
    }


    /**
     * 就是Fibonacci数列
     *
     * @param n
     * @return
     */
    public int climb_stairs(int n) {
        if (n <= 2) {
            return n;
        }
        return climb_stairs(n - 1) + climb_stairs(n - 2);
    }

    public int fibonacci(int n) {
        if (n <= 2) {
            return n;
        }
        int p = 1;
        int q = 2;
        int r = 0;
        for (int i = 0; i < n - 2; i++) {
            r = p + q;
            p = q;
            q = r;
        }
        return r;
    }

    /**
     * 使用Fibonacci通项公式 fn = 1/sqrt(5)( ((1+sqrt(5)) / 2)^n - ((1-sqrt(5)) / 2)^n) )
     */
    class Solution70_3 {
        public int climbStairs(int n) {
            double sqrt5 = Math.sqrt(5);
            double fibn = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
            return (int) (fibn / sqrt5);
        }

    }
}

/**
 * 动态规划
 */
class Solution70_4 {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];

    }

}

//leetcode submit region end(Prohibit modification and deletion)
