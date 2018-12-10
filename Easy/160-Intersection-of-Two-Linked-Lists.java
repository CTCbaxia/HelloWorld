/*
EASY
160. Intersection of Two Linked Lists
https://leetcode.com/problems/construct-string-from-binary-tree/description/

TIME: 1011 - 15min
RESULT: 100% - 1ms
NOTES: 
找到长度，然后从同一个起点开始
*/
/*
计算长度 + 从同一个地方开始

Time: O(n)
Space: O(1)
*/
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB){
        int counta = 0;
        int countb = 0;
        ListNode nodea = headA;
        ListNode nodeb = headB;

        while(nodea != null){
            counta++;
            nodea = nodea.next;
        }
        while(nodeb != null){
            countb++;
            nodeb = nodeb.next;
        }
        nodea = headA;
        nodeb = headB;
        if(counta >= countb){
            int diff = counta - countb;
            while(diff-- > 0) nodea = nodea.next;
        }else{
            int diff = countb - counta;
            while(diff-- > 0) nodeb = nodeb.next;
        }

        while(nodea != null && nodeb != null){
            if(nodea == nodeb) return nodea;
            nodea = nodea.next;
            nodeb = nodeb.next;
        }
        return null;
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