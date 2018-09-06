/*
MEDIUM
148. Sort List
https://leetcode.com/problems/sort-list/description/

TIME: 0905 - 1h
RESULT: 97% - 4ms
NOTES:
http://bigocheatsheet.com
1. merge sort 的 time O(n logn)
这种题要学会把问题分解成小问题。用最终的小问题思维来解决。
*/
//merge sort
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        while(fast != null && fast.next != null){
            pre = slow;//存前一半 list 的最后一位
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;//切断
        
        head = sortList(head);
        slow = sortList(slow);
        
        return merge(head, slow);
        
    }
    private ListNode merge(ListNode node1, ListNode node2){//相当于 merge List 那题
        ListNode n1 = node1;
        ListNode n2 = node2;
        ListNode move = new ListNode(0);
        ListNode res = move;
        while(n1 != null && n2 != null){
            if(n1.val <= n2.val){
                move.next = n1;
                move = move.next;
                n1 = n1.next;
            }else{
                move.next = n2;
                move = move.next;
                n2 = n2.next;
            }
        }
        if(n1 == null){
            move.next = n2;
        }else if(n2 == null){
            move.next = n1;
        }
        return res.next;
    }
}