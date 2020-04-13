package l03.n206;//反转一个单链表。
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */


class Solution206 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 1. 递归，时间复杂度O(n),空间复杂度O(n)
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode rest = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return rest;
    }


    /**
     * 2. 循环
     * 假设存在链表 1 → 2 → 3 → Ø，我们想要把它改成 Ø ← 1 ← 2 ← 3。
     * <p>
     * 在遍历列表时，将当前节点的 next 指针改为指向前一个元素。由于节点没有引用其上一个节点，因此必须事先存储其前一个元素。
     * 在更改引用之前，还需要另一个指针来存储下一个节点。不要忘记在最后返回新的头引用！
     * <p>
     * 时间复杂度O(n) 空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
