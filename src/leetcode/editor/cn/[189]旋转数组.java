package leetcode.editor.cn;//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
//
// 示例 1: 
//
// 输入: [1,2,3,4,5,6,7] 和 k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 输入: [-1,-100,3,99] 和 k = 2
//输出: [3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 说明: 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 要求使用空间复杂度为 O(1) 的 原地 算法。 
// 
// Related Topics 数组


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 1. 暴力，每次将数组旋转一个元素
 * 时间复杂度O(k*n) 空间复杂度O(1)
 */
class Solution1 {
    public void rotate(int[] nums, int k) {
        for (int i = 0; i < k % nums.length; i++) {
            int last = nums[nums.length - 1];
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = last;
        }
    }
}

/**
 * 2. 使用额外数组，原数组下标为i的元素拷贝到（i+k)%length位置上，再把新数组拷贝到原数组
 * 时间复杂度O(n),空间复杂度O(n)
 */
class Solution2 {
    public void rotate(int[] nums, int k) {
        int[] e = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            e[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = e[i];
        }
    }
}

/**
 * 3. 反转法
 * 旋转数组k次，k%n个尾部元素被移动到头部，剩下的元素移动到尾部
 * 原始数组                  : 1 2 3 4 5 6 7
 * 反转所有数字后             : 7 6 5 4 3 2 1
 * 反转前 k 个数字后          : 5 6 7 4 3 2 1
 * 反转后 n-k 个数字后        : 5 6 7 1 2 3 4 --> 结果
 * <p>
 * 时间复杂度O(n)，空间复杂度O(1)
 */
class Solution3 {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);

    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}

/**
 * 4. 环装替换
 *
 * @see <a href="https://pic.leetcode-cn.com/f0493a97cdb7bc46b37306ca14e555451496f9f9c21effcad8517a81a26f30d6-image.png">图片</a>
 * 时间复杂度O(n),空间复杂度O(1)
 */
//leetcode submit region end(Prohibit modification and deletion)
class Solution4 {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[current];
            do {
                int next = (k + current) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }

    }
}