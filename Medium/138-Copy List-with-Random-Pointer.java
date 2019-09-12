/*
MEDIUM
138. Copy List with Random Pointer
https://leetcode.com/problems/copy-list-with-random-pointer/description/

TIME: 
RESULT: 

*/

/*
Iteration - HashMap to match original and the copy one

Time: O(n)
Space: O(n)
*/
class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        
        //copy all nodes
        Node node = head;
        while(node != null){
            Node copyNode = new Node();
            copyNode.val = node.val;
            map.put(node, copyNode);
            node = node.next;
        }
        //assign next and random
        node = head;
        while(node != null){
            Node copyNode = map.get(node);
            copyNode.next = map.get(node.next);
            copyNode.random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);//return the copy of the head
    }
}
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/



/*
Iteraction - 锯齿形 matching
** assign originalNode's next to the copyNode to replace the map
** copyNode.next = originalNode's next
node  next
 |  /  |
copy  copy's next

Time: O(n)
Space: O(1)
*/
class Solution {
    public Node copyRandomList(Node head) {
        //create copy node and make node.next = nodeCopy, connect copy node with its next
        Node node = head;
        while(node != null){
            Node next = node.next;
            Node nodeCopy = new Node(node.val, next, null);//copy.next = original.next
            node.next = nodeCopy;//assign node.next as the copy of the node
            node = next;
        }
        
        //assign random for copy node
        node = head;
        while(node != null){
            if(node.random != null) node.next.random = node.random.next;//need to check null
            node = node.next.next;//move to the original.next
        }
        
        //assign next for the copy node
        node = head;
        Node dummy = new Node(0, null, null);
        Node copy = dummy;
        while(node != null){
            Node nodeCopy = node.next;
            Node next = nodeCopy.next;
            node.next = next;
            copy.next = nodeCopy;
            
            copy = copy.next; 
            node = next;
        }
        return dummy.next;
    }
}



/*
Recursion - HashMap to match original and the copy one, to avoid circle leading to dead loop

thinking every node as node in graph, every node has two edges, we may have circle so we need to mark visited ones

Time: O(n)
Space: O(n)
*/
class Solution {
    Map<Node, Node> map = new HashMap<>();//to avoid circle, otherwise we may have dead loop
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        if(map.containsKey(head)) return map.get(head);
        
        Node copyHead = new Node(head.val, null, null);
        map.put(head, copyHead);//should be put to map before assign next/random for recursion
        
        copyHead.next = copyRandomList(head.next);
        copyHead.random = copyRandomList(head.random);
        
        return copyHead;
    }
}
























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