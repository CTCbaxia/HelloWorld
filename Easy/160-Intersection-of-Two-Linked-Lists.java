/*
EASY
606-Construct-String-from-Binary-Tree.java
https://leetcode.com/problems/construct-string-from-binary-tree/description/

TIME: 1011 - 15min
RESULT: 100% - 1ms
NOTES: 
找到长度，然后从同一个起点开始
*/
//其实只要让他们同时到达 inetrsection就好
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        if(a == null || b == null) return null;
        while(a != b){
            a = (a == null)? headB : a.next;
            b = (b == null)? headA : b.next;
        }
        return a;
    }
    
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        int counta = 0;
        int countb = 0;
        while(a != null){
            a = a.next;
            counta++;
        }
        while(b != null){
            b = b.next;
            countb++;
        }
        
        a = headA;
        b = headB;
        while(counta > countb){
            a = a.next;
            counta--;
        }
        while(countb > counta){
            b = b.next;
            countb--;
        }
        while(a != b){
            a = a.next;
            b = b.next;
        }
        return a;
    }
}