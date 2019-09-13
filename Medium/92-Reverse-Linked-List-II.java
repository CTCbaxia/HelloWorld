/*
MEDIUM
92. Reverse Linked List II
https://leetcode.com/problems/reverse-linked-list-ii/description/

TIME: 0715 - 5h
RESULT: 96% - 2ms

*/
/*
Reverse one by one

用 node hold 住 m，每次都把 m 后面的 node 移到 dummyhead 的后面，然后衔接

Time: O(n)
Space: O(1)
*/
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        int k = n - m;//need to swap k times
        
        ListNode res = new ListNode(0);//hold return value
        res.next = head;
        ListNode dummyHead = res;//hold node before m
        
        //find right dummyHead and node
        while(m-- > 1){
            dummyHead = dummyHead.next;
        }
        ListNode node = dummyHead.next;//hold m
        while(k-- > 0){
            ListNode nextNode = dummyHead.next;
            dummyHead.next = node.next;
            node.next = node.next.next;
            dummyHead.next.next = nextNode;
        }
        return res.next;
    }
}









/*
preHead + newHead 逐步交换法

Time: O(n)
Space: O(1)
*/
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n){
        ListNode dummyHead = new ListNode(0);//以防要交换的从第一个元素开始
        ListNode preHead = dummyHead;
        dummyHead.next = head;

        int k = n - m;//要交换的次数
        while(m-- > 1) preHead = preHead.next;//走到 m 之前

        ListNode newHead = preHead.next;//永远连在 preHead 之后
        ListNode node = preHead.next;//m 一直往后挪
        ListNode then = node.next;//m 之后的要挪到 preHead 之后的node
        while(k-- > 0){
            preHead.next = then;
            node.next = then.next;
            then.next = newHead;
            newHead = then;

            then = node.next;
        }
        return dummyHead.next;
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



//reference : 就是要在 x.next 赋值之前把他给托付出去
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode result = new ListNode(0);
        result.next = head;
        ListNode pre = result;
        for(int i = 0; i < m - 1; i++){
            pre = pre.next;
        }
        ListNode start = pre.next;
        ListNode then = start.next;
        for(int i = 0; i < n - m; i++){
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }
        
        return result.next;
    }

}

class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        int count = n - m;
        ListNode result = new ListNode(0);
        result.next = head;
        ListNode res = result;
        while(n >= 0){
            res = res.next;
            n--;
        }
        ListNode start = result;
        while(m > 1){
            start = start.next;
            m--;
        }
        
        ListNode node = start.next;
        System.out.println(node.val);
        
        while(count >= 0){
            ListNode nextHead = node.next;
            node.next = res;
            System.out.println(node.val);
            res = node;
            node = nextHead;
            
            count--;
        }
        start.next = res;
        return result.next;
    }

}
/*
SOLUTION 1
思路：
sublist：遍历到 m，并在 mn 翻转之后链接 n 端的 node
reverse：反转存 node
forrest：指向 m node（reverse 的第一个 node），等着翻转之后回收尾部 node
tmp：遍历要反转的 list（m->n）并在 n node 处等待遍历结束

TIME: 0715 - 2h
RESULT: 96%
NOTES:
1. 对于链表，注意链表的指针，区分到底是想要转移到一个新链表，还是想更改该链表的后续 node（前者 sublist； 后者 sublist.next）
2. 链表可以翻转的
3. 链表的操作注意其不回溯性，要暂时存储之后还会用到的节点的位置
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
        
        ListNode sublist = new ListNode(0);
        sublist.next = head;
        head = sublist;
        ListNode reverse = new ListNode(0);
        ListNode forrest = reverse;
        ListNode tmp = new ListNode(0);
        for(int i = 1; i <= n; i++){
            if(i <= m){
                if(i == m){
                    tmp = sublist.next;
                    reverse.val = tmp.val;
                }else{
                    sublist = sublist.next;
                }
            }else{
                tmp = tmp.next;
                if(i > m && i <=n){
                    reverse = reverseNode(tmp.val, reverse);
                }
                if(i == n){
                    sublist.next = reverse;
                    forrest.next = tmp.next;
                    break;
                } 
            }
        }
        return head.next;
    }
    private ListNode reverseNode(int val, ListNode l){
        ListNode res = new ListNode(0);
        res.val = val;
        res.next = l;
        return res;
    }
}




/*
SOLUTION 2:
思路：先遍历翻转部分的后一半，讲 node 存到 stack 里面。
然后从 m 开始 stack.pop，并同时存储 m ~ index 部分的 node

TIME: 0715 - 3h
RESULT: 14%
NOTES:
1. 注意奇偶部分的区分，关键节点
2. 注意 stack 里面存的数量


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



/*
REFERENCE 1：
NOTE: 
链表的 next 不停地转换 pointer 以达到按自己需求链接 next node 的目的
*/


public ListNode reverseBetween(ListNode head, int m, int n) {
    if(head == null) return null;
    ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
    dummy.next = head;
    ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
    for(int i = 0; i<m-1; i++) pre = pre.next;
    
    ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
    ListNode then = start.next; // a pointer to a node that will be reversed
    
    // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
    // dummy-> 1 -> 2 -> 3 -> 4 -> 5
    
    for(int i=0; i<n-m; i++)
    {
        start.next = then.next;
        then.next = pre.next;
        pre.next = then;
        then = start.next;
    }
    
    // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
    // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)
    
    return dummy.next;
    
}



The basic idea is to build a sub-list when we hit Node m by adding the subsequent nodes to the head of the sub-list one by one until we hit Node n. Then connect the nodes before Node m, the sub-list and the nodes following Node n.


public ListNode reverseBetween(ListNode head, int m, int n) {
    ListNode dummyhead = new ListNode(0);
    dummyhead.next = head;
    ListNode sublisthead = new ListNode(0);
    ListNode sublisttail = new ListNode(0);
    int count = 1;
    ListNode pre_cur = dummyhead, cur = head;
    while(count <=n){
        ListNode temp = cur.next;
        if (count < m)
            pre_cur = cur;
        else if (count == m){
            sublisttail = cur;
            sublisthead.next = cur;
        }else if (count > m){
            cur.next = sublisthead.next;
            sublisthead.next = cur;
        }
        cur = temp;
        ++count;
    }
    pre_cur.next = sublisthead.next;
    sublisttail.next = cur;
    return dummyhead.next;
}
