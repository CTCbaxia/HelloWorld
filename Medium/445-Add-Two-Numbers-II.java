/*
MEDIUM
445. Add Two Numbers II

TIME: 
RESULT: 
NOTES:
1. Stack - easier
2. Reverse Result - Space better but extremly complicated
*/


/*
Reverse Result: too complex

Built the reverse listnode first
And deal with the carry from head to null
And reverse to the final result

Time: O(n)
Space: O(1)
*/
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	//find out who is shorter
        ListNode c1 = l1;
        ListNode c2 = l2;
        while(c1 != null && c2 != null){
            c1 = c1.next;
            c2 = c2.next;
        }

        //deal with the head part:
        //build the result listnode with the (多余的) head of longer listnode
        //at the same time, move the pointer to the same starter point of the shorter point
        // 1 - 2 - 3 - 4 存下多余的头部 1， 然后起点为 2
        //     2 - 3 - 6 起点为 2
        ListNode longer = (c1 == null) ? l2 : l1;//start with the head of the longer one
        ListNode tail = (c1 == null) ? c2 : c1;//store the tail of the longer one, 当 tail 遍历到结尾，longer 就是同一个起始点
        ListNode shorter = (c1 == null) ? l1 : l2;//shorter 的起始点
        ListNode end = null;//倒着 build 结果，方便处理 carry
        while(tail != null){

        	//build end
            ListNode node = new ListNode(longer.val);
            node.next = end;
            end = node;
            
            longer = longer.next;
            tail = tail.next;
        }

        //continuously build the raw reversed result
        while(shorter != null){
            ListNode node = new ListNode(longer.val + shorter.val);
            node.next = end;
            end = node;
            
            longer = longer.next;
            shorter = shorter.next;
        }
        
        //end
        //10 - 6 - 4 - 1
        //then deal with the carry
        ListNode node = end;
        int carry = 0;
        while(node != null){
            int num = node.val + carry;
            node.val = num % 10;
            carry = num / 10;
            
            node = node.next;
        }
        
        //0 - 7 - 4 - 1
        //reverse the result
        ListNode res = null;
        while(end != null){
            ListNode newHead = end.next;
            end.next = res;
            res = end;
            end = newHead;
        }

        //1 - 4 - 7 - 0
        //add last carry
        if(carry != 0){
            ListNode c = new ListNode(carry);
            c.next = res;
            res = c;
        }

        //possible carry - 1 - 4 - 7 - 0
        return res;
        
    }
}


/*
Stack: more intuitive!!

we first put everything into the stack
and we build up the listnode from end to the first

Time: O(n)
Space: O(n) as we use stacks
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        
        while(l1 != null){
            s1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null){
            s2.push(l2.val);
            l2 = l2.next;
        }
        ListNode res = null;
        int carry = 0;
        while(!s1.isEmpty() || !s2.isEmpty()){
            int num = carry;
            if(!s1.isEmpty()) num += s1.pop();
            if(!s2.isEmpty()) num += s2.pop();
            
            ListNode node = new ListNode(num % 10);
            carry = num / 10;
            
            node.next = res;
            res = node;
        }
        if(carry > 0){
            ListNode node = new ListNode(carry);
            node.next = res;
            res = node;
        }
        return res;
    }
}