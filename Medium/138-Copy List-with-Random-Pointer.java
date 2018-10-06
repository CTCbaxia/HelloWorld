/*
MEDIUM
138. Copy List with Random Pointer
https://leetcode.com/problems/copy-list-with-random-pointer/description/

TIME: 
RESULT: 

*/
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