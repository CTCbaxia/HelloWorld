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
/*
SOLUTION 2
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
        
        ListNode sublist = head;
        ListNode reverse = new ListNode(0);
        ListNode forrest = reverse;
        ListNode tmp = new ListNode(0);
        for(int i = 1; i <= n; i++){
            if(i < m){
                if(i == m - 1){
                    
                    tmp = sublist.next;
                    sublist.next = new ListNode(0);
                }
                sublist = sublist.next;
                
            }else{
                if(i == m) reverse.val = tmp.val;
                if(i > m && i <=n){
                    reverse = reverseNode(tmp.val, reverse);
                }
                if(i == n){
                    sublist = reverse;
                    forrest.next = tmp.next;
                    break;
                } 
                tmp = tmp.next;
            }
            
        }
        return head;
    }
    private ListNode reverseNode(int val, ListNode l){
        ListNode res = new ListNode(0);
        res.val = val;
        res.next = l;
        return res;
    }
}







/*
REFERENCE
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
