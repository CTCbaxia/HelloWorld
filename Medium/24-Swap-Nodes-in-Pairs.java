/*
MEDIUM
24. Swap Nodes in Pairs
https://leetcode.com/problems/swap-nodes-in-pairs/description/

TIME: 1006 - 15min
RESULT: 70% - 2ms
思路：这种题需要找好几个pointer
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
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;//only one node
        
        ListNode result = new ListNode(0);
        result.next = head;
        ListNode tmpHead = result;

        while(tmpHead.next != null && tmpHead.next.next != null){
            ListNode tmpPre = tmpHead.next;
            ListNode tmpNext = tmpPre.next;
            
            tmpHead.next = tmpNext;
            tmpPre.next = tmpNext.next;
            tmpNext.next = tmpPre;
            
            tmpHead = tmpPre;
        }
        return result.next;
    }
}


/*
SOLUTION REFERENCE

*/
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;//only one node
        ListNode node = head.next;
        head.next = swapPairs(head.next.next);
        node.next = head;
        return node;
    }
}



//不用recursive 还是很复杂，需要有三个pointer
//head : 遍历指针
//node: 交换指针
//preHead : 链接上文
//result: 总返回值
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;//only one node
        ListNode result = new ListNode(0);
        result.next = head;
        ListNode preHead = result;
        while(head != null && head.next != null){
            ListNode node = head.next;
            head.next = head.next.next;
            node.next = head;           
            preHead.next = node;
            
            preHead = head;
            head = head.next;
        }

        return result.next;
    }
}