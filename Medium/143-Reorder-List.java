./*
MEDIUM
143. Reorder List

TIME: 
RESULT: 
NOTES:
1. 要注意不要成环，成环你永远不会走到 null。应该画出示意图
*/
/*
1. Reverse the second half
2. attach second to first

**be careful to cutoff the list
**be careful to run to the next

Time: O(n)
Space: O(1)
*/
class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        
        ListNode fast = head, slow = head;
        while(fast.next != null && fast.next.next != null){//确保 fast 永远踩在点上
            fast = fast.next.next;
            slow = slow.next;
        }
        //reverse second
        ListNode second = slow.next;
        slow.next = null;
        
        ListNode reverse = null;
        while(second != null){
            ListNode newSecond = second.next;
            second.next = reverse;
            reverse = second;
            second = newSecond;
        }
        second = reverse;//be careful to assign second here
        
        //merge first and second
        ListNode first = head;
        while(second != null){//second is shorter/equal than first
            ListNode newSecond = second.next;//这块就跟swap螺旋一样，永远保证现有的值被用过再改变
            second.next = first.next;
            first.next = second;
            second = newSecond;
            first = first.next.next;//because first.next = second (not null)
        }
        return;
    }
}







/*
separate + reverse + merge
Time: O(n)
Space: O(1)
*/
class Solution {
    public void reorderList(ListNode head){
        if(head == null || head.next == null) return;
        
        //separate to first and second
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null){
          fast = fast.next.next;
          slow = slow.next;
        }
        ListNode second = slow.next;
        slow.next = null;

        //reverse the second
        ListNode res = null;
        while(second != null){
            ListNode newHead = second.next;
            second.next = res;
            res = second;
            second = newHead;
        }
        second = res;
        
        //merge
        ListNode first = head;
        while(first != null && second != null){
            ListNode tmpS = second.next;
            second.next = first.next;
            first.next = second;
            second = tmpS;
            first = first.next.next;
        }    
        return;
    }
  
}









/*
Time: O(n)
Space: O(1)

RESULT: 100% - 2ms
 */
class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        //find the middle point
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;//odd:正中点; even:前半结尾
        }
        
        //reverse the second half
        ListNode res = null;//the head of the reversed second half
        ListNode node = slow.next;
        slow.next = null;//separate first and second half
        while(node != null){
            ListNode newHead = node.next;
            node.next = res;
            res = node;
            node = newHead;
        }
        
        //merge two list: head and res
        node = head;
        while(node != null && res != null){
            ListNode then = node.next;
            node.next = res;
            res = res.next;
            node.next.next = then;
            node = then;
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