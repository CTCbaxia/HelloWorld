/*
M
1145. Binary Tree Coloring Game
*/
/*
Greedy + DFS
Count the left and right subtree of the current x,
you can maximize the y's nodes by choosing either x's left, right, parent node
you can win if any of this node with its subtree (think parent own another type of subtree) has number of nodes >= n/2
left: leftNodes
right: rightNodes
parent: n - leftNodes - rightNodes - 1


Tip:
you can find the x along the way of counting nodes

Time: O(n)
Space: O(logn) for recursion
*/
class Solution {
    int left = 0, right = 0;
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        //find the x, and count left and right subtree of x
        count(root, x);
        
        int parent = n - (left + right + 1);
        return Math.max(Math.max(left, right), parent) > n/2;
    }

    private int count(TreeNode node, int x){
        if(node == null) return 0;
        int l = count(node.left, x);
        int r = count(node.right, x);
        if(node.val == x){
            left = l;
            right = r;
        }
        return 1 + l + r;
    }
}





/*
Count the number of nodes for subtree of x (including x), let's set is as num, 
if num <= n/2, then you can win by just set y as x's parent
if num > n/2,
    if one of the x's subtree is larger than n/2, you can win
    else you lose
    
Time: O(n)
Space: O(logn) for recursion
*/
class Solution {
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        //find the x
        TreeNode nodeX = find(root, x);
        
        //count number
        int left = count(nodeX.left);
        int right = count(nodeX.right);
        int subTreeX = left + right + 1;
        
        if(subTreeX <= n/2) return true;
        else{
            if(left > n/2 || right > n/2) return true;
            else return false;
        }
    }
    private TreeNode find(TreeNode node, int x){
        if(node == null) return null;
        if(node.val == x) return node;
        
        TreeNode left = find(node.left, x);
        if(left != null) return left;
        
        TreeNode right = find(node.right, x);
        if(right != null) return right;
        
        return null;
    }
    private int count(TreeNode node){
        if(node == null) return 0;
        return 1 + count(node.left) + count(node.right);
    }
}
