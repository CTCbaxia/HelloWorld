/*
MEDIUM
143. Reorder List

TIME: 
RESULT: 
NOTES:
1. 要注意不要成环，成环你永远不会走到 null。应该画出示意图
*/
/*
Time: O(n)
Space: O(1)

RESULT: 100% - 2ms
 */
class Solution {
    public void reorderList(ListNode head) {
        if(head == null) return;
        //find the middle point
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;//odd:正中点; even:前半结尾
        }
        
        //reverse the second half of the listnode
        ListNode second = slow;
        slow = slow.next;
        ListNode res = null;//the head of the reversed second half
        ListNode newHead = slow;
        while(slow != null){
            newHead = slow.next;
            slow.next = res;
            res = slow;
            slow = newHead;
        }
        second.next = null;
        second = res;
        
        //make the final list
        ListNode first = head;
        while(second != null){
            ListNode newFirst = first.next;
            first.next = second;
            second = second.next;
            first.next.next = newFirst;  
            first = newFirst;
        }
        return;
    }

}


/*
一个一个变，每次用一个end走到尾，interative
*/
class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return;
        ListNode node = head;
        ListNode preend = node;
        while(preend.next != null && preend.next.next != null){
            preend = preend.next;
        }
        preend.next.next = node.next;
        node.next = preend.next;
        preend.next = null;

        reorderList(node.next.next);
        return;
    }

}
/*
一个一个变，每次用一个end走到尾
Time: O(n^2)
Space: O(1)
RESULT: 1% - 635ms
*/
class Solution {
    public void reorderList(ListNode head) {
        if(head == null) return;
        ListNode node = head;
        ListNode next = node.next;
        ListNode preend;
        
        while(next != null && next.next != null){
            preend = next;
            while(preend.next != null && preend.next.next != null){
                preend = preend.next;
            }
            //reorder
            node.next = preend.next;
            preend.next.next = next;
            preend.next = null;
            //reinitiate
            node = next;
            next = node.next;
        }
        return;
    }
}