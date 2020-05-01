package l01.n523;//给定一个包含非负数的数组和一个目标整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，总和为 k 的倍数，即总和为 n*k，其中 n
//也是一个整数。 
//
// 示例 1: 
//
// 输入: [23,2,4,6,7], k = 6
//输出: True
//解释: [2,4] 是一个大小为 2 的子数组，并且和为 6。
// 
//
// 示例 2: 
//
// 输入: [23,2,6,4,7], k = 6
//输出: True
//解释: [23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
// 
//
// 说明: 
//
// 
// 数组的长度不会超过10,000。 
// 你可以认为所有数字总和在 32 位有符号整数范围内。 
// 
// Related Topics 数学 动态规划


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution523 {
    public boolean checkSubarraySum(int[] nums, int k) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0 && nums[i + 1] == 0) {
                return true;
            }
        }
        if (k == 0) {
            return false;
        }
        Map<Integer, Integer> m = new HashMap<>();
        // 防止第一个数就是k的情况
        m.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int mod = sum % k;
            if (m.containsKey(mod)) {
                if (i - m.get(mod) > 1) {
                    return true;
                }
            } else {
                m.put(mod, i);
            }
        }
        return false;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
