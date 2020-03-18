package l01.n15;//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 哈希表
 */
class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new LinkedHashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < nums.length; j++) {
                int target = 0 - nums[i] - nums[j];
                if (map.get(target) == null) {
                    map.put(nums[j], nums[j]);
                } else {
                    List<Integer> l = new ArrayList<>();
                    l.add(nums[i]);
                    l.add(nums[j]);
                    l.add(target);
                    l.sort(Comparator.naturalOrder());
                    res.add(l);
                }
            }
        }
        return new ArrayList<>(res);
    }
}

/**
 * 排序+双指针
 */
class Solution15_1 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 1. 先排序
        Arrays.sort(nums);
        // 2. 3个指针，k，i，j。分别指向当前元素，当前元素下一个和最后一个
        for (int k = 0; k < nums.length - 2; k++) {
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum == 0) {
                    List<Integer> l = Arrays.asList(nums[k], nums[i], nums[j]);
                    res.add(l);
                    // i向前，j向后，遇到与之前相等的一直移动
                    while (i < j && nums[i] == nums[++i]) ;
                    while (i < j && nums[j] == nums[--j]) ;
                } else if (sum > 0) {
                    // sum过大，将j向前缩小
                    while (i < j && nums[j] == nums[--j]) ;
                }else {
                    // sum过小，向后移动i
                    while (i < j && nums[i] == nums[++i]) ;
                }

            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
