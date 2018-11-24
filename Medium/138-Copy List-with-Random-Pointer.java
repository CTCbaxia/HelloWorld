/*
MEDIUM
138. Copy List with Random Pointer
https://leetcode.com/problems/copy-list-with-random-pointer/description/

TIME: 
RESULT: 

*/

/*
Map build all node and have the matching relationship from original to the copied one

Time: O(n)
Space: O(n)
*/
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        if(head == null) return null;
        
        //copy the node
        RandomListNode node = head;
        while(node != null){
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }
        
        //build the relationship
        node = head;
        while(node != null){
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        RandomListNode copy = new RandomListNode(0);
        copy.next = map.get(head);
        return copy.next;
    }
}





//不行 会成环
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        RandomListNode copyHead = new RandomListNode(head.label);
        copyHead.next = copyRandomList(head.next);
        copyHead.random = copyRandomList(head.random);
        return copyHead;
    }
}
/*
SOLUTION REFERENCE:
ListNode 的问题就是无法直接获取某一个元素。那么map可以解决这个问题。
想要对应一个node，就用mapping 的方法
O(n)

*/
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return head;
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        
        //loop for the first time to copy all the nodes
        RandomListNode node = head;
        while(node != null){
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }
        //loop for assigning the value
        node = head;
        while(node != null){
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        RandomListNode copyHead = new RandomListNode(0);
        copyHead.next = map.get(head);
        return copyHead.next;
    }
}