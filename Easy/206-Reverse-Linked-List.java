/*
EASY
206. Reverse Linked List
https://leetcode.com/problems/reverse-linked-list/description/

TIME: 0822 - 1.5h
RESULT: 
NOTES:
recursive: 利用 method, 每次递归，得到更前一层的结果。可以理解为倒着来
iterative: 正着得到结果
注意 ListNode 的翻转都要利用 next 来分离以及存储 头部 和 身体。

*/
/*
SOLUTION REFERENCE: 
TIME: 0822 - 20min
RESULT: 100% - 0ms
METHOD: recursive
*/
class Solution {
    public ListNode reverseList(ListNode head){
        return recursive(head, null);
    }
    private ListNode recursive(ListNode remainHead, ListNode newHead){
        if(remainHead == null) return newHead;
        ListNode next = remainHead.next;
        remainHead.next = newHead;
        newHead = remainHead;
        return recursive(next, newHead);
    }
}
/*
SOLUTION REFERENCE: 
TIME: 0822 - 20min
RESULT: 100% - 0ms
METHOD: iterative
*/
class Solution {
    public ListNode reverseList(ListNode head){
        ListNode result = null;
        while(head != null){
            ListNode nextHead = head.next;
            head.next = result;
            result = head;
            head = nextHead;
        }
        return result;
    }
}


/**
SOLUTION 1: 
TIME: 0822 - 30min
RESULT: 100% - 0ms
METHOD: recursive
 */
 */
class Solution {
    public ListNode reverseList(ListNode head){
        ListNode result = new ListNode(0);
        if(head == null) return result.next;
        
        result.val = head.val;
        head = head.next;
        while(head != null){
            result = recursive(head, result);
            head = head.next;
        }
        return result;
        
    }
    private ListNode recursive(ListNode head, ListNode node){
        ListNode res = new ListNode(head.val);
        res.next = node;
        return res;
    }
}
/*
SOLUTION 0:
TIME: 0822 - 30min
RESULT: 9% - 3ms
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode result = new ListNode(0);
        ListNode loop = new ListNode(0);
        if(head == null) return result.next;
        else result.next = loop;

        Stack<Integer> stack = new Stack<Integer>();
        while(head != null){
            stack.push(head.val);
            head = head.next;
        }
        
        while(!stack.empty()){
            loop.val = stack.pop();
            if(!stack.empty()){
                loop.next = new ListNode(0);
                loop = loop.next;
            }
        }
        return result.next;
    }

}