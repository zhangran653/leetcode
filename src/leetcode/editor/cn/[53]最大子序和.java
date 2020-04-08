package leetcode.editor.cn;//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
// 示例: 
//
// 输入: [-2,1,-3,4,-1,2,1,-5,4],
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
// 
//
// 进阶: 
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。 
// Related Topics 数组 分治算法 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution53 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //1 .定义状态，dp[i]表示前i个元素最大和
        int[] dp = new int[nums.length];
        // 2. 初始化
        dp[0] = nums[0];
        int res = dp[0];
        //3. 状态转移方程
        // dp[i] = max(dp[i-1],0)+nums[i]
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            if (dp[i] > res) {
                res = dp[i];
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
