/*
MEDIUM
86. Partition List
https://leetcode.com/problems/partition-list/description/

TIME: 0903 - 20min
RESULT: 100% - 0ms
*/
/*
SOLUTION 0: 
TIME: 0903 - 20min
RESULT: 100% - 0ms
For this list: 5->6->1->2, x=3, at last cur2 points to 6, cur1 points to 2, we must set 6->1 to 6->null, otherwise there will be a cycle.
*/
class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null) return head;
        ListNode less = new ListNode(0);
        ListNode more = new ListNode(0);
        ListNode result = less;
        ListNode connect = more;

        while(head != null){
            System.out.println(head.val);
            if(head.val < x){
                less.next = head;
                less = less.next;
            }else if(head.val >= x){
                more.next = head;
                more = more.next;
            }
            head = head.next;
        }
        more.next = null; //important! avoid cycle in linked list
        less.next = connect.next;//如果没有这一句，这题在 [1,4,3,2,5,2] 中会形成环

        return result.next;
    }
}

//每次新建自己的 ListNode，更加保险，防止混乱或成环。缺点是没有空间复杂度高。
class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null) return head;
        ListNode less = new ListNode(0);
        ListNode more = new ListNode(0);
        ListNode result = less;
        ListNode connect = more;
        while(head != null){
            if(head.val < x){
                ListNode node = new ListNode(head.val);//其实也可以不用新建，直接在 head 这条上面调整。但是这种情况永远要多加小心
                less.next = node;
                less = less.next;
            }else if(head.val >= x){
                ListNode node = new ListNode(head.val);
                more.next = node;
                more = more.next;
            }
            head = head.next;
        }
        less.next = connect.next;
        
        
        return result.next;
    }
}