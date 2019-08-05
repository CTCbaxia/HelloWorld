/*
M
979. Distribute Coins in Binary Tree
*/
/*
Recursion
the return value is int
    1) if int > 0, we can provide coins to you
    2) if int < 0, we need more coins from you
Time: O(n)
Space: O(1), recursion stack O(logn)
*/
class Solution {
    int res;
    public int distributeCoins(TreeNode root) {
        res = 0;
        dfs(root);
        return res;
    }
    private int dfs(TreeNode node){
        if(node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        int move = left + right + node.val - 1;
        res += Math.abs(move);
        return move;
    }
}


/*
Recursion -- Using int[] to pass by reference so there is no need to use global variable
the return value is int
    1) if int > 0, we can provide coins to you
    2) if int < 0, we need more coins from you
    
Time: O(n)
Space: O(1), recursion stack O(logn)
*/
class Solution {
    public int distributeCoins(TreeNode root) {
        int[] res = dfs(root, 0);
        return res[1];
    }
    private int[] dfs(TreeNode node, int curMove){
        if(node == null) return new int[]{0, curMove};
        int[] left = dfs(node.left, curMove);
        curMove = left[1];
        
        int[] right = dfs(node.right, curMove);
        curMove = right[1];
        
        int move = left[0] + right[0] + node.val - 1;// the number of coins we provide to / request from the upper level
        curMove += Math.abs(move);
        
        return new int[]{move, curMove};
    }
}
