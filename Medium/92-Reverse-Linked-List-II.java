/*
MEDIUM
92. Reverse Linked List II
https://leetcode.com/problems/reverse-linked-list-ii/description/

TIME: 0715 - 3h
RESULT: 14%
NOTES:
METHOD:

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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == n) return head;
        Stack<Integer> reverse_a = new Stack<Integer>();
        Stack<Integer> reverse_b = new Stack<Integer>();
        
        int index = (m + n) / 2;
        int length = (n - m) % 2 == 0 ? (n - m) / 2 :  (n - m) / 2 + 1;
        
        ListNode reverse = head;
        for(int i = 1; i <= index; i++) reverse = reverse.next;
        System.out.println("here");
        System.out.println("here: " + reverse.val);
        for(int j = 0; j < length; j++){
            reverse_a.push(reverse.val);
            reverse = reverse.next;
        }
        System.out.println("reverse_a:" + reverse_a);
        ListNode new_list = head;
        for(int k = 1; k <= n; k++){
            if(k >= m){
                if((n - m) % 2 == 0){
                    
                    if(k < index){
                        reverse_b.push(new_list.val);
                        new_list.val = reverse_a.pop();
                    }else if(k > index){
                        new_list.val = reverse_b.pop();
                    }
                }else{
                    if(k <= index){
                        reverse_b.push(new_list.val);
                        new_list.val = reverse_a.pop();
                    }else if(k > index){
                        new_list.val = reverse_b.pop();
                    }
                }
            
            }
            //System.out.println("reverse_b:" + reverse_b);
            new_list = new_list.next;
            
        }
        return head;
    }
}
