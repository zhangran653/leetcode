package l01.n55;//给定一个非负整数数组，你最初位于数组的第一个位置。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个位置。 
//
// 示例 1: 
//
// 输入: [2,3,1,1,4]
//输出: true
//解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
// 
//
// 示例 2: 
//
// 输入: [3,2,1,0,4]
//输出: false
//解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
// 
// Related Topics 贪心算法 数组


//leetcode submit region begin(Prohibit modification and deletion)
class Solution55 {
    public boolean canJump(int[] nums) {
        if (nums == null) {
            return false;
        }
        int right = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] + i >= right) {
                right = i;
            }
        }
        return right == 0;
    }


    /**
     * 如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
     * 可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
     * 如果可以一直跳到最后，就成功了。
     * <p>
     * 作者：ikaruga
     * 链接：https://leetcode-cn.com/problems/jump-game/solution/55-by-ikaruga/
     *
     * @param nums
     * @return
     */
    public boolean canJump1(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                return false;
            }
            k += Math.max(k, i + nums[i]);
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
