/*
M
662. Maximum Width of Binary Tree
*/
/*
BFS: Queue
Put the index/number of the node into the queue
node = n
node.left = 2n
node.right = 2n + 1

Time: O(n)
Space: O(n)
*/
class Solution {
    public class NodeNum{
        TreeNode node;
        int num;
        public NodeNum(TreeNode _node, int _num){
            node = _node;
            num = _num;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        
        int res = 0;
        Queue<NodeNum> q = new LinkedList<>();
        q.offer(new NodeNum(root, 1));
        while(!q.isEmpty()){
            int size = q.size();
            int start = 0, end = 0;
            for(int i = 0; i < size; i++){
                NodeNum nm = q.poll();
                if(i == 0) start = nm.num;
                if(i == size - 1) end = nm.num;
                if(nm.node.left != null) q.offer(new NodeNum(nm.node.left, nm.num * 2));
                if(nm.node.right != null) q.offer(new NodeNum(nm.node.right, nm.num * 2 + 1));
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
}


/*
DFS: List
Put the index/number of the node into the queue
node = n
node.left = 2n
node.right = 2n + 1

Time: O(n)
Space: O(n)
*/
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        dfs(root, 1, 0, start, end);
        int res = 0;
        for(int i = 0; i < start.size(); i++){
            res = Math.max(res, end.get(i) - start.get(i) + 1);
        }
        return res;
    }
    private void dfs(TreeNode node, int curVal, int level, List<Integer> start, List<Integer> end){
        if(node == null) return;
        if(start.size() == level){
            start.add(curVal);//initialize start
            end.add(curVal);
        }else{
            end.set(level, curVal);//update the end
        }
        dfs(node.left, curVal * 2, level + 1, start, end);
        dfs(node.right, curVal * 2 + 1, level + 1, start, end);
    }
}
