package l01.n78;//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//] 
// Related Topics 位运算 数组 回溯算法


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution78 {
    /**
     * 递归回溯
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null) {
            return ans;
        }
        sub(ans, new ArrayList<>(), 0, nums);
        return ans;
    }

    private void sub(List<List<Integer>> ans, List<Integer> list, int index, int[] nums) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        sub(ans, list, index + 1, nums);
        list.add(nums[index]);
        sub(ans, list, index + 1, nums);
        list.remove(list.size() - 1);
    }

    /**
     * 迭代
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        for (int num : nums) {
            List<List<Integer>> newset = new ArrayList<>();
            for (List<Integer> sub : ans) {
                List<Integer> s = new ArrayList<>(sub);
                s.add(num);
                newset.add(s);
            }
            ans.addAll(newset);
        }

        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
