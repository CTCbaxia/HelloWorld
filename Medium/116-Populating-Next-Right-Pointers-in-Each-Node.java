/*
MEDIUM
116. Populating Next Right Pointers in Each Node
https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/

TIME: 0828 - 2h
RESULT: 100% - 0ms
NOTE:
1. 注意每次迭代之间的联系，上一个 level 已经搭好了从左到右的桥，下一个 level 在非左右字数的搭桥时，可以依赖上一层的桥
*/


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