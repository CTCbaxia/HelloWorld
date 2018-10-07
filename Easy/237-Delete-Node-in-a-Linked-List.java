/*
EASY
237. Delete Node in a Linked List
https://leetcode.com/problems/delete-node-in-a-linked-list/description/

TIME: 1007 - 5min
RESULT: 100% - 0ms
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
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
        return;
    }
}