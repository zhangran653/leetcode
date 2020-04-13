package l01.n84;//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 
//
// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。 
//
// 
//
// 
//
// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。 
//
// 
//
// 示例: 
//
// 输入: [2,1,5,6,2,3]
//输出: 10 
// Related Topics 栈 数组


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution84 {
    /**
     * 暴力
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minHeight = Math.min(heights[j], minHeight);
                maxArea = Math.max((j - i + 1) * minHeight, maxArea);
            }
        }
        return maxArea;
    }

    /**
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        int maxArea = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(-1);
        for (int i = 0; i < heights.length; i++) {
            while (deque.getLast() != -1 && heights[deque.getLast()] >= heights[i]) {
                int height = heights[deque.removeLast()];
                maxArea = Math.max(maxArea, height * (i - deque.getLast() - 1));
            }
            deque.addLast(i);
        }
        while (deque.getLast() != -1) {
            int height = heights[deque.removeLast()];
            maxArea = Math.max(maxArea, height * (heights.length - deque.getLast() - 1));
        }
        return maxArea;
    }


    public static void main(String[] args) {
        int res = new Solution84().largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3});
        System.out.println(res);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
