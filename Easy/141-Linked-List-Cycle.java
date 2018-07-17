/*
EASY
141. Linked List Cycle
https://leetcode.com/problems/linked-list-cycle/description/

TIME: 0717 - 1h
RESULT: 90.98% - 1ms
NOTES:
1. 快慢指针法要好好体会
2. HashSet<Element> 里面可以是 ListNode

思路:
怎么确定 last.next == somenode.next??
1. 记录遍历的每个 node ?? hashmap？？ 
2. 如果 node.next.val 出现过，很可能就成环过。但是如果这个 val 出现过多次呢，怎么记录每个出现的位置？？List<List>??

换个思路：环状和线状的区别？？
环状会回到起点，周而复始；线状会越走越远（地球是圆的）。所以环状 link 一直走下去总有可能再次相遇。线状会一直走到 null
那就设一个 tmp 一直向前走，直到走到 null 退出。---【判断线型的终止情况】
但是如果是环状，可能就永远不会遇到 null (死循环)。所以还差环形的终止情况。

怎么避免环状情况下的死循环。
1. 记录所有遍历过的 node，一旦重复出现，则成环
2. 设置一个守望者告诉你，你回来啦

对于第二个方法，再换个思路：环形跑道和线性跑道
线型：跑得慢的和跑得快的永远不会再次相遇
环形：跑得慢的和跑得快的会再次相遇 ---【判断环形的终止情况】

*/

/*
METHOD: 双指针
RESULT: 90.98% - 1ms

Complexity analysis:
1.Time complexity : O(n)
在最糟糕的情形下，时间复杂度为 O(N+K)，也就是 O(n)。
2.Space complexity: O(1). 
我们只使用了慢指针和快指针两个节点

*/
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode faster = head;
        ListNode slower = head;
        boolean start = true;
        
        while(faster != slower || start){
            start = false;
            int i = 0;
            while(i < 2 && faster != null){
                faster = faster.next;
                i++;
            }
            if(faster == null) return false;    

            slower = slower.next;
            
        }
        return true;
    }
}

/*
METHOD: 哈希表

Complexity analysis:
1.Time complexity : O(n)
We visit each of the n elements in the list at most once. Adding a node to the hash table costs only O(1) time.

2.Space complexity: O(n). 
The space depends on the number of elements added to the hash table, which contains at most n elements. 

RESULT: 12% - 6ms
*/
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode move = head;
        Set<ListNode> allnode = new HashSet<ListNode>();
        
        while(move != null){
            
            if(!allnode.contains(move)) allnode.add(move);
            else return true;
            move = move.next;
        }
        return false;
    }
}