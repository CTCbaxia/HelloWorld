/*
M
1161. Maximum Level Sum of a Binary Tree
*/
/*
BFS
Time: O(n)
Space: O(1)
*/
class Solution {
    public int maxLevelSum(TreeNode root) {
        if(root == null) return 0;
        
        int resLevel = 1, curLevel = 1;
        int maxSum = Integer.MIN_VALUE;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            int sum = 0;
            for(int i = 0; i < size; i++){
                TreeNode node = q.poll();
                sum += node.val;
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
            if(sum > maxSum){
                maxSum = sum;
                resLevel = curLevel;
            }
            curLevel++;
        }
        return resLevel;
    }
}
