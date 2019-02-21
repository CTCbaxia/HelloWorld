/*
EASY
234. Palindrome Linked List

*/
/*
Two Pointers -- reverse the second half linked list

Time: O(n)
Space: O(1)
*/
   
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null) return true;
        
        ListNode fast = head, slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = slow.next;
        
        //reverse the second half
        ListNode second = null;
        while(slow != null){
            ListNode newHead = slow.next;
            slow.next = second;
            second = slow;
            slow = newHead;
        }
        
        while(second != null){
            if(second.val != head.val) return false;
            second = second.next;
            head = head.next;
        }
        return true;
    }
}






/*
Two Pointers

Time: O(n)
Space: O(n)
*/

class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null) return true;
        
        ListNode fast = head, slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode second = slow.next;
        
        //go for second half
        List<Integer> reverse = new ArrayList<>();
        while(second != null){
            reverse.add(second.val);
            second = second.next;
        }
        
        //go for first half
        for(int i = reverse.size() - 1; i >= 0; i--){
            if(reverse.get(i) != head.val) return false;
            head = head.next;
        }
        return true;
    }
}

