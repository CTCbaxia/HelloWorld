/*
MEDIUM
958. Check Completeness of a Binary Tree
*/
/*
BFS - Level Order Traversal
when we meet a null, all nodes in the current q after null shuold be null
(there should be no node after null)

Time: O(n)
Space: O(n)
*/
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty() && q.peek() != null){
            TreeNode node = q.poll();
            q.offer(node.left);
            q.offer(node.right);
        }
        while(!q.isEmpty() && q.peek() == null){
            q.poll();
        }
        return q.size() == 0;
    }
}

/* CTC
BFS - Level Order Traversal
termination: when a node get no child/no right child
1. the rest nodes in this level should not have children
2. the next level should be empty (the previous nodes in this level should all have no children)

一旦 terminate 了，queue 里面剩下的 nodes 都不能再有 children

Time: O(n)
Space: O(n)
*/
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if(root == null) return true;
        boolean isTerminate = false;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode node = q.poll();
                if(node.left != null){
                    if(isTerminate) return false;
                    else q.offer(node.left);
                }else{
                    isTerminate = true;
                }
                if(node.right != null){
                    if(isTerminate) return false;
                    else q.offer(node.right);
                }else{
                    isTerminate = true;
                }
            }
        }
        return true;
    }
}