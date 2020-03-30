package l01.n39;//给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的数字可以无限制重复被选取。 
//
// 说明： 
//
// 
// 所有数字（包括 target）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: candidates = [2,3,6,7], target = 7,
//所求解集为:
//[
//  [7],
//  [2,2,3]
//]
// 
//
// 示例 2: 
//
// 输入: candidates = [2,3,5], target = 8,
//所求解集为:
//[
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
//] 
// Related Topics 数组 回溯算法


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        // 排序，提前剪枝
        Arrays.sort(candidates);
        backtrack(result, new LinkedList<>(), target, 0, candidates);
        return result;
    }


    /**
     * @param ans
     * @param temp
     * @param residue
     * @param pos
     * @param candidates
     */
    private void backtrack(List<List<Integer>> ans, List<Integer> temp, int residue, int pos, int[] candidates) {

        if (residue == 0) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        if (residue < 0) {
            return;
        }
        for (int i = pos; i < candidates.length; i++) {
            // 剩余的数如果比排序好的candidates[i] 还要小，说明后面所有的candidate都不合适
            if (residue < candidates[i]) {
                continue;
            }
            int candidate = candidates[i];
            temp.add(candidate);
            // pos为i，表示下一次还可以重复使用当前的数
            backtrack(ans, temp, residue - candidate, i, candidates);
            temp.remove(temp.size() - 1);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
