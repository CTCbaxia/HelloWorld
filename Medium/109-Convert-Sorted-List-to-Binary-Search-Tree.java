/*
MEDIUM
109. Convert Sorted List to Binary Search Tree
https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/

TIME: 0901 - 20min
RESULT: 100% - 1ms

*/
/*
DFS + 全局变量遍历listnode，范围控制每个subtree遍历的长度

Time: O(n)
Space: O(logn)
*/

class Solution {
    ListNode node; 
    public TreeNode sortedListToBST(ListNode head){
        node = head;
        ListNode countNode = head;
        int count = 0;
        while(countNode != null){//数有多少个nodes
            count++;
            countNode = countNode.next;
        }
        return dfs(0, count - 1);
    }        
    private TreeNode dfs(int start, int end){//loop listnode, in order traverse build tree
        if(start > end) return null;
        
        int mid = start + (end - start)/2;
        TreeNode left = dfs(start, mid - 1);
        
        TreeNode root = new TreeNode(node.val);
        node = node.next;
        
        TreeNode right = dfs(mid + 1, end);
        
        root.left = left;
        root.right = right;
        return root;
    }
}




/*
Fast + Slow 找中点

Time: O(nlogn)  T(n) = 2T(n/2) + O(n)
Space: O(logn)
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
    public TreeNode sortedListToBST(ListNode head){
        return build(head, null);
    }        

    private TreeNode build(ListNode head, ListNode tail){
        if(head == null || head == tail) return null;

        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != tail && fast.next.next != tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = build(head, slow);
        root.right = build(slow.next, tail);
        return root;
    }
}



//METHOD: 快慢指针
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        return findMid(head, null);
    }
    private TreeNode findMid(ListNode start, ListNode end){
        if(start == end) return null;
        
        ListNode slow = start;
        ListNode fast = start;
        while(fast != end){
            if(fast.next == end){
                fast = fast.next;
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = findMid(start, slow);
        root.right = findMid(slow.next, end);
        return root;
    }
}


    while(fast!=tail&&fast.next!=tail){//其实只有 fast 可以走两步的时候 slow 才会走，而最终的 root 生成只看 slow  值
        fast = fast.next.next;
        slow = slow.next;
    }