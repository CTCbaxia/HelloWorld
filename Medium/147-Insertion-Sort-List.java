/*
MEDIUM
147. Insertion Sort List
https://leetcode.com/problems/insertion-sort-list/description/

TIME: 0905 - 30min
RESULT: 50% - 30ms
NOTES:
链表的操作，无非就是要注意记住保留你需要的起点。
在每次操作的时候切忌成环。
*/

//更简洁一点
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null) return head;
        ListNode sortHead = new ListNode(0);
        ListNode compare = sortHead;
        ListNode insert = head;
        while(head != null){
            while(compare.next != null && insert.val >= compare.next.val){
                compare = compare.next;
            }
            head = head.next;
            insert.next = compare.next;
            compare.next = insert;
            
            //initialize for next loop
            compare = sortHead;
            insert = head;
        }
        return sortHead.next;
    }
}
//loop the input list from left to right
//maintain the head of the output list and loop it till find the right place
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null) return head;
        ListNode sortHead = new ListNode(head.val);
        head = head.next;
        while(head != null){
            
            ListNode compare = sortHead;
            ListNode insert = new ListNode(head.val);
            if(compare.val >= insert.val) {
                insert.next = compare;
                sortHead = insert;
                head = head.next;
            }else{
                while(compare != null && compare.val < head.val){
                        insert = compare;
                        compare = compare.next;
                }
            
                insert.next = head;

                head = head.next;
                insert.next.next = compare;
            }
        }
        return sortHead;
    }
}