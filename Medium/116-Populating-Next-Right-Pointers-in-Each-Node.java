/*
MEDIUM
116. Populating Next Right Pointers in Each Node
https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/

TIME: 0828 - 2h
RESULT: 100% - 0ms
NOTE:
1. 注意每次迭代之间的联系，上一个 level 已经搭好了从左到右的桥，下一个 level 在非左右字数的搭桥时，可以依赖上一层的桥
*/
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
/*
BFS:
每层遍历，跟着 node.next 走每一层
在每一层遍历的时候，构建下一层的 node.next

Time: O(n)
Space: O(1)
*/
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode levelHead = root;
        TreeLinkNode node = levelHead;//for every level, store the head
        while(levelHead.left != null){//levelHead has next relation already. See if we have next level to build
            //connect children level from left to right following node.next
            while(node != null){
                node.left.next = node.right;
                if(node.next != null) node.right.next = node.next.left;
                node = node.next;
            }//end of this level
            node = levelHead.left;//move to the next level
            levelHead = node;
        }
        return;
    }
}








/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode node = root;
        while(node != null){
            //connect left and right
            if(node.left != null){
                node.left.next = node.right;
            }
            if(node.next != null){
                node.right.next = node.next.left;
                node = node.next;
            }else node = node.left;
        }
        return;
    }
}








/*
BFS
put every level into queue from right to the left

Time: O(n)
Space: O(n)
*/
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> q = new LinkedList<>();
        if(root == null) return;
        q.offer(root);
        while(!q.isEmpty()){
            Queue<TreeLinkNode> newQ = new LinkedList<>();
            TreeLinkNode end = null;
            while(!q.isEmpty()){
                TreeLinkNode node = q.poll();
                node.next = end;
                end = node;
                
                //put children into newQ
                if(node.right != null) newQ.offer(node.right);
                if(node.left != null) newQ.offer(node.left);
            }
            q = newQ;
        }
        return;
    }
}








/*
SOLUTION 0:
TIME: 0828 - 2h
RESULT: 100% - 0ms
*/
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        while(root != null){
            TreeLinkNode pre = null;
            TreeLinkNode theLeft = root;
            while(root != null){//这个不能放在子函数里
                pre = nextLevel(root, pre);
                root = root.next;
            }
            root = theLeft.left;
        }
        return;
    }
    private TreeLinkNode nextLevel(TreeLinkNode root, TreeLinkNode pre){
        if(root.left == null) return pre;
        if(pre != null) pre.next = root.left;
        root.left.next = root.right;
        pre = root.right;
        return pre;
    }
}


/*
Wrong Code to help you learn:
*/

public class Solution {
    public void connect(TreeLinkNode root) {
        while(root != null){
            TreeLinkNode pre = null;
            System.out.println("start a new level   root.val:" + root.val);
            nextLevel(root, pre);
            root = root.left;
        }
        return;
    }
    private void nextLevel(TreeLinkNode root, TreeLinkNode pre){
        if(root.left == null) return;
        if(pre != null){
            pre.next = root.left;
            System.out.println("root.val:" + root.val + "  pre.val" + pre.val);
        }
        root.left.next = root.right;
        pre = root.right;
        while(root.next != null){//这个不能放在子函数里，因为会在遍历子函数的 root 的时候多重循环
            root = root.next;
            nextLevel(root, pre);
        }
        return;
    }
}

TEST CASE: {-1,0,1,2,3,4,5,6,7,8,9,10,11,12,13}
OUTPUT:    {-1,#,0,1,#,2,3,4,5,#,6,7,12,13,#}
EXPECTED:  {-1,#,0,1,#,2,3,4,5,#,6,7,8,9,10,11,12,13,#}


PRINT:
root.val:1  pre.val3
start a new level   root.val:2
root.val:3  pre.val7 //root = 2 的时候的 while 层里面
root.val:4  pre.val9 //root = 3 的时候的 while 层里面
root.val:5  pre.val11 //root = 4 的时候的 while 层里面
root.val:5  pre.val9  //回到 root = 3 的时候的下一个遍历（root = root.next 之后）
root.val:4  pre.val7
root.val:5  pre.val11
root.val:5  pre.val7
start a new level   root.val:6