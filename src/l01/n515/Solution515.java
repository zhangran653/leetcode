package l01.n515;//您需要在二叉树的每一行中找到最大的值。
//
// 示例： 
//
// 
//输入: 
//
//          1
//         / \
//        3   2
//       / \   \  
//      5   3   9 
//
//输出: [1, 3, 9]
// 
// Related Topics 树 深度优先搜索 广度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution515 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        bfs(res, root);
        return res;
    }

    private void bfs(List<Integer> res, TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            while (size-- > 0) {
                TreeNode n = queue.poll();
                if (n.val > max) {
                    max = n.val;
                }
                if (n.left != null) {
                    queue.offer(n.left);
                }
                if (n.right != null) {
                    queue.offer(n.right);
                }
            }
            res.add(max);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
