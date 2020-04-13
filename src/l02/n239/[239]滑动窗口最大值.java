package l02.n239;//给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
//
//
// 返回滑动窗口中的最大值。 
//
// 
//
// 示例: 
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7] 
//解释: 
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7 
//
// 
//
// 提示： 
//
// 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。 
//
// 
//
// 进阶： 
//
// 你能在线性时间复杂度内解决此题吗？ 
// Related Topics 堆 Sliding Window


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 1.暴力
 * 时间复杂度O(n*k)
 * 空间复杂度O(n)
 */
class Solution239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            // 判断当前队列中队首的值是否有效
            if (queue.getFirst() <= i - k) {
                queue.pollFirst();
            }
            // 当窗口长度为k时 保存当前窗口中最大值
            if (i + 1 >= k) {
                res[i + 1 - k] = nums[queue.getFirst()];
            }

        }

        return res;
    }

}

/**
 * 2.双端队列
 * 时间复杂度O(n)
 * 空间复杂度O(n-k+1)
 */
class Solution239_1 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length < 2) {
            return nums;
        }
        // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
        Deque<Integer> queue = new ArrayDeque<>();
        int[] r = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            // 判断当前队列中队首的值是否有效
            if (queue.getFirst() <= i - k) {
                queue.pollFirst();
            }
            // 当窗口长度为k时 保存当前窗口中最大值
            if (i + 1 >= k) {
                r[i + 1 - k] = nums[queue.getFirst()];
            }

        }
        return r;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
