/*
MEDIUM
61. Rotate List

TIME: 1007 - 20min
RESULT: 99% - 9ms
NOTES:
1. 快慢指针的思想
2. 记得用数学方法简化内容
3. 要在一开始想清楚需要几个指针
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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return head;
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode faster = newHead;
        ListNode slower = newHead;
        //get the len
        int count = 0;
        while(faster.next != null){
            faster = faster.next;
            count++;
        }
        k = k % count;
        if(k == 0) return head;
        
        //find the end
        int step = count - k;
        while(step > 0){
            slower = slower.next;
            step--;
        }
        //rotate
        newHead.next = slower.next;
        slower.next = null;
        faster.next = head;
        
        return newHead.next;
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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return head;
        //calculate how many nodes in list
        ListNode countNode = head;
        int count = 0;
        while(countNode != null){
        	countNode = countNode.next;
        	count++;
        }
        k = k % count;
        if(k == 0) return head;
        
        int step = count - k - 1;
        ListNode end = head;
        
        while(step-- > 0){
            end = end.next;
        }
        ListNode newHead = end.next;
        end.next = null;
        ListNode connect = newHead;
        while(connect.next != null) connect = connect.next;
        connect.next = head;
        
        return newHead;
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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;

        //calculate how many nodes in list
        ListNode countNode = head;
        int count = 0;
        while(countNode != null){
        	countNode = countNode.next;
        	count++;
        }
        k = k % count;

        while(k > 0){
            ListNode end = head.next;
            ListNode pre = head;
            while(end.next != null){
                pre = pre.next;
                end = end.next;
            }
            pre.next = null;
            end.next = head;
            
            head = end;
            k--;
        }
        return head;
    }
}