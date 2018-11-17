/*
MEDIUM
199. Binary Tree Right Side View
https://leetcode.com/problems/binary-tree-right-side-view/description/

TIME: 0830 - 15min
RESULT: 78% - 1ms
METHOD: 
1. DFS
2. BFS
*/
/*
DFS + level vs list size
dfs 先右后左，记录 level
如果这个 level 在 result 里面还没有，说明他是第一个出现在这个 level 的
直接加到result

Time: O(n)
Space: O(1)
*/
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }
    private void dfs(TreeNode node, int level, List<Integer> result){
        if(node == null) return;
        if(level == result.size()) result.add(node.val);
        
        dfs(node.right, level + 1, result);
        dfs(node.left, level + 1, result);
        return;
    }
}

/*
BFS + add the last one in the queue (q.size() == 0)

Time: O(n)
Space: O(n)
*/
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        if(root != null) q.offer(root);
        
        while(!q.isEmpty()){
            Queue<TreeNode> newQ = new LinkedList<>();
            while(!q.isEmpty()){
                TreeNode node = q.poll();
                if(q.size() == 0) result.add(node.val);
                if(node.left != null) newQ.offer(node.left);
                if(node.right != null) newQ.offer(node.right);
            }
            q = newQ;
        }
        return result;
    }
}














class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        
        queue.add(root);
        while(!queue.isEmpty()){
            
            Queue<TreeNode> nextlevel = new LinkedList<TreeNode>();
            while(!queue.isEmpty()){
                TreeNode node = queue.poll();
                if(queue.size() == 0) result.add(node.val);
                if(node.left != null) nextlevel.add(node.left);
                if(node.right != null) nextlevel.add(node.right);
            }
            queue = nextlevel;
        }
        return result;
    }
}


//similar but use len
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        
        queue.add(root);
        while(!queue.isEmpty()){
            int len = queue.size();
            for(int i = 0; i < len; i++){
                TreeNode node = queue.poll();
                if(i == len - 1) result.add(node.val);
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
        }
        return result;
    }
}