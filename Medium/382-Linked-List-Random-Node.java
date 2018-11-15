/*
MEDIUM
382. Linked List Random Node

TIME: 
RESULT: 
NOTES:

*/
/*
Reservoir Sampling: 
update the result while looping

Time: O(n) - one pass
Space: O(1)
 */
class Solution {
    ListNode head;
    Random rand;
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;
        rand = new Random();
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode node = head;
        
        int index = 1;
        int result = 0;
        while(node != null){
            if(rand.nextInt(index) == 0){
                result = node.val;
            }
            node = node.next;
            index++;
        }
        return result;
    }
}

/*
Find the len of the ListNode, random(len)

Time: O(n) - two pass
Space: O(1)
 */
class Solution {
    int len;
    ListNode head;
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;
        
        ListNode node = head;
        while(node != null){
            len++;
            node = node.next;
        }
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode node = new ListNode(0);
        node.next = head;
        
        Random rdm = new Random();
        int num = rdm.nextInt(len);
        while(num-- >= 0){
            node = node.next;
        }
        return node.val;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */