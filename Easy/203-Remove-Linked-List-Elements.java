/*
EASY
203. Remove Linked List Elements
https://leetcode.com/problems/remove-linked-list-elements/description/

TIME: 1007 - 30min
RESULT: 99% - 4ms
NOTES: 
RECURSIVE
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//SOLUTION REFERENCE
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;
        head.next = removeElements(head.next, val);
        if(head.val == val) return head.next;
        else return head;
    }
}

//SOLUTION 0:
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;
        if(head.val == val){
            if(head.next == null) head = null;
            else{
                ListNode node = removeElements(head.next, val);
                if(node != null){
                    head.val = node.val;
                    head.next = node.next;  
                }else head = null;
            }
        }else{
            head.next = removeElements(head.next, val);
        }
        return head;
    }
}