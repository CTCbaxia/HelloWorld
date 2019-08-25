/*
M
328. Odd Even Linked List
*/
/*
two pointers to hold odd and even

Time: O(n)
Space: O(1)
*/
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null) return head;
        ListNode odd = head, even = head.next;
        ListNode evenHead = even;
        while(even != null && even.next != null){
            odd.next = even.next;
            even.next = even.next.next;
            odd.next.next = evenHead;
            
            odd = odd.next;
            even = even.next;
        }
        return head;
    }
}
