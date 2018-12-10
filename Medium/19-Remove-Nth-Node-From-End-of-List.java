/*
MEDIUM
19. Remove Nth Node From End of List

TIME: 1007 - 20min
RESULT: 99% - 8ms

*/
/*
Fast and Slow pointer

Time: O(n)
Space: O(1)
*/
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n){
        if(head == null || n == 0) return head;
        ListNode result = new ListNode(0);
        result.next = head;

        ListNode fast = result;
        ListNode slow = result;
        while(n-- > 0){//2
            fast = fast.next;
            if(fast == null) return head;//if n is not always valid
        } 
        while(fast.next != null){
            fast= fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return result.next;
    }  
}



class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode faster = head;
        ListNode slower = head;
        while(n-- >= 0){//直接让他多走一步，这样子 slow 就会落在前面一位
            faster = faster.next;
        }
        
        while(faster != null){
            faster = faster.next;
            slower = slower.next;
        }
        slower.next = slower.next.next;
        
        return head;
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode faster = head;
        ListNode slower = head;
        while(n-- > 0){
            faster = faster.next;
        }
        if(faster == null) return head.next;
        
        while(faster.next != null){
            faster = faster.next;
            slower = slower.next;
        }
        slower.next = slower.next.next;
        
        return head;
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode go = new ListNode(0);
        if(n <= 0){
            return head;
        }else if( n == 1){
            go = head;
        }else{
            go = head.next;
            n = n - 1;
        }
        
        while( n - 1 > 0 && (go.next != null)){
            go = go.next;
            n --;
        }
        if(go.next == null){
            return head.next;
        }
        ListNode follow = new ListNode(0);
        follow.next = head;
        while(go.next != null){
            go = go.next;
            follow = follow.next;
        }
        follow.next = follow.next.next;
        return head;    
    }
}