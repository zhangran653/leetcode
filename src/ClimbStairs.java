import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

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

        Arrays.sort(steps);
        int bound = steps.length;   // right bound of valid steps

        int[] dp = new int[n + 1];
        Arrays.fill(dp, 0);
        int c = 0;
        dp[c] = 1;

        // sparse
        while (true) {
            // find the smallest reachable step
            while (c < n && dp[c] == 0)
                c++;
            if (c >= n)
                break;
            // look forward
            for (int i = 0; i < bound; i++) {
                int s = steps[i];
                if (c + s > n) {
                    // update the right bound
                    bound = i;
                    break;
                }
                dp[c + s] += dp[c];
            }
            c++;
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

        // partition n using elements in steps
        // each f[i,j] is an map, name it m, m[k] means j can be partitioned with the first i elements in steps, and
        // there are m[k] schemes with size k, i.e., there are m[k] ways to partition j into k parts of the first i elements in steps
        // typically, we do not need the dimension of i, we iterate over steps
        // FIXME: using of Map is quite heavy, we should make it smarter
        TreeMap<Integer, Integer>[] f = new TreeMap[n + 1];
        TreeSet<Integer> reachable = new TreeSet<>();
        // 0 is reachable with not step
        reachable.add(0);
        f[0] = new TreeMap<Integer, Integer>();
        // 1 scheme with 0 elements
        f[0].put(0, 1);

        for (int s : steps) {
            // g is the case when `s` is taken
            TreeMap<Integer, Integer>[] g = new TreeMap[n + 1];
            TreeSet<Integer> newReachable = new TreeSet<>();
            for (int r : reachable) {
                int c = r + s;
                if (f[r] != null && c <= n) {
                    g[c] = mergeInto(f[r], 1, g[c] == null ? new TreeMap<>() : g[c]);
                    newReachable.add(c);
                }
            }
            reachable.addAll(newReachable);
            // merge g into f (f corresponds to `s` is not taken)
            for (int r : reachable) {
                if (f[r] == null) {
                    f[r] = g[r];
                } else if (g[r] != null) {
                    f[r] = mergeInto(f[r], 0, g[r]);
                }
            }
        }

        // count the solution
        Map<Integer, Integer> sol = f[n];
        if (sol == null)
            return 0;
        int result = 0;
        for (Map.Entry<Integer, Integer> e : sol.entrySet()) {
            int size = e.getKey();
            int count = e.getValue();
            result += factorial(size) * count;
        }
        return result;
    }

    /**
     * shift keys in diff, merge diff into base and return base
     */
    private TreeMap<Integer, Integer> mergeInto(TreeMap<Integer, Integer> diff, int shift, TreeMap<Integer, Integer> base) {
        for (Map.Entry<Integer, Integer> e : diff.entrySet()) {
            int size = e.getKey() + shift;
            int count = e.getValue();
            base.put(size, base.getOrDefault(size, 0) + count);
        }
        return base;
    }

    private int factorial(int n) {
        int r = 1;
        for (int i = 2; i <= n; i++) {
            r *= i;
        }
        return r;
    }

    public static void main(String[] args) {
        // 2,8 - 2
        // 3,7 - 2
        // 4,6 - 2
        // 1,2,7 - 6
        // 1,3,6 - 6
        // 1,4,5 - 6
        // 2,3,5 - 6
        // 1,2,3,4 - 24
        // sum = 54
        int r = new ClimbStairs().climbStairs3(3, new int[]{1, 2});
        System.out.println(r);
    }

}