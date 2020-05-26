package l01.n105;//根据一棵树的前序遍历与中序遍历构造二叉树。
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution105 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, map, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int pLeft, int pRight, Map<Integer, Integer> map, int iLeft, int iRight) {
        if (pLeft > pRight || iLeft > iRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pLeft]);

        root.left = buildTree(preorder, pLeft + 1, map.get(preorder[pLeft]) - iLeft + pLeft,
                map, iLeft, map.get(preorder[pLeft]) - 1);
        root.right = buildTree(preorder, map.get(preorder[pLeft]) - iLeft + pLeft+1, pRight, map,
                map.get(preorder[pLeft]) + 1, iRight);
        return root;

    }


}
//leetcode submit region end(Prohibit modification and deletion)
