/*
EASY
83. Remove Duplicates from Sorted List

TIME: 
RESULT:
NOTES: 

*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode result = new ListNode(0);
        result.next = head;
        while(head != null){
            if(head.next != null && head.next.val == head.val){
                head.next = head.next.next;
            }else{
                head = head.next;
            }
        }
        return result.next;
    }
}