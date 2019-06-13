/*
MEDIUM
863. All Nodes Distance K in Binary Tree
*/
/**
DFS to find Target (along with its Parents list)
BFS to find specific level of nodes for each parent(including the target itself)
** need to not include the lastNode we went (don't go back)

Time: O(n + 2^(logn + 1)) = O(n)
Space: O(n/2)
*/
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<TreeNode> list = new ArrayList<>();
        findTarget(root, list, target);
            
        List<Integer> res = new ArrayList<>();
        int levels = K;
        TreeNode lastNode = null;
        for(int i = list.size() - 1; i >= 0 && K >= 0; i--){
            TreeNode curNode = list.get(i);
            List<Integer> levelRes = new ArrayList<>();
            findLevelNodes(curNode, lastNode, levelRes, K--);
            
            res.addAll(levelRes);
            lastNode = curNode;//update the node side that contains the target (should not be include)
        }
        return res;
    }
    private boolean findTarget(TreeNode node, List<TreeNode> list, TreeNode target){
        if(node == null) return false;
        
        list.add(node);
        if(node == target) return true;
        if(findTarget(node.left, list, target)){
            return true;
        }
        if(findTarget(node.right, list, target)){
            return true;
        }
        list.remove(node);
        return false;
    }
    private void findLevelNodes(TreeNode root, TreeNode notIncludeNode, List<Integer> levelNodes, int level){
        if(root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty() && level > 0){
            int len = q.size();
            level--;
            for(int i = 0; i < len; i++){
                TreeNode node = q.poll();
                if(node.left != null && node.left != notIncludeNode) q.offer(node.left);
                if(node.right != null && node.right != notIncludeNode) q.offer(node.right);
            }
        }
        while(!q.isEmpty()){
            levelNodes.add(q.poll().val);
        }
        
        return;
    }
}