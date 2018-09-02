/*
MEDIUM
109. Convert Sorted List to Binary Search Tree
https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/

TIME: 0901 - 20min
RESULT: 100% - 1ms
METHOD: 快慢指针
*/

class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        return findMid(head, null);
    }
    private TreeNode findMid(ListNode start, ListNode end){
        if(start == end) return null;
        
        ListNode slow = start;
        ListNode fast = start;
        while(fast != end){
            if(fast.next == end){
                fast = fast.next;
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = findMid(start, slow);
        root.right = findMid(slow.next, end);
        return root;
    }
}


    while(fast!=tail&&fast.next!=tail){//其实只有 fast 可以走两步的时候 slow 才会走，而最终的 root 生成只看 slow  值
        fast = fast.next.next;
        slow = slow.next;
    }