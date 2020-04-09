package leetcode.editor.cn;//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 示例 1: 
//
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 13
//输出: false 
// Related Topics 数组 二分查找


//leetcode submit region begin(Prohibit modification and deletion)
class Solution74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int l = 0, r = matrix.length;
        int midIndex = -1;
        while (l < matrix.length && l <= r) {
            int m = l + (r - l) / 2;
            int[] mid = matrix[m];
            if (mid[0] <= target && mid[mid.length - 1] >= target) {
                midIndex = m;
                break;
            } else if (mid[0] > target) {
                r = m - 1;
            } else if (mid[mid.length - 1] < target) {
                l = m + 1;
            }
        }
        if (midIndex == -1) {
            return false;
        }
        int[] n = matrix[midIndex];
        l = 0;
        r = n.length;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (n[m] == target) {
                return true;
            } else if (n[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        int[][] ma = {{1}};
        new Solution74().searchMatrix(ma, 2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
