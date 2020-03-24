package l01.n145;//给定一个二叉树，返回它的 后序 遍历。
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [3,2,1] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * 1.初始两个栈s1,s2
 * 2.栈s1保存根结点
 * 3.栈s1弹出p=s1.top()并将p存入栈s2，p->left先入栈s1，p->right再入栈s1
 * 4.重复步骤3，直至栈s1为空
 * 5.栈s2按序弹出，并存入在容器res中
 * 6.重复步骤5，直至栈s2为空
 * 7.输出容器res
 */
class Solution145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        Deque<TreeNode> s1 = new ArrayDeque<>();
        Deque<TreeNode> s2 = new ArrayDeque<>();

        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        s1.push(root);
        while (!s1.isEmpty()) {
            TreeNode p = s1.pop();
            s2.push(p);
            if (p.left != null) {
                s1.push(p.left);
            }
            if (p.right != null) {
                s1.push(p.right);
            }
        }
        while (!s2.isEmpty()) {
            res.add(s2.pop().val);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
