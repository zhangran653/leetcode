package leetcode.editor.cn;//编写一个程序，找出第 n 个丑数。
//
// 丑数就是只包含质因数 2, 3, 5 的正整数。 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
// Related Topics 堆 数学 动态规划


import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution264 {
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        long res = 1;
        for (int i = 1; i < n; i++) {
            queue.add(res * 2);
            queue.add(res * 3);
            queue.add(res * 5);
            res = queue.poll();
            // 去重
            while (!queue.isEmpty() && res == queue.peek()) {
                queue.poll();
            }
        }
        return (int)res;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
