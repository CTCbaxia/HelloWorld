/*
HARD
124. Binary Tree Maximum Path Sum

TIME: 1023 - 40min
RESULT: 41% - 2ms
NOTES: tree 的题一定要简洁
*/
/*
single path: path only from root to one of the leaf(if > 0)
result: node.val + left + right (if > 0)

Time: O(n)
Space: O(1)
*/
class Solution {
    int result = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        singlePath(root);
        return result;
    }
    private int singlePath(TreeNode node, int){
        //get the map of <node, largest single path sum with node itself>
        if(node == null) return 0;
        int left = singlePath(node.left);
        int right = singlePath(node.right);
        int res = node.val + Math.max(0, Math.max(left, right));
        result = Math.max(result, node.val + Math.max(0, left) + Math.max(0, right));
        return res;
    }
}


/*
single path: path only from root to one of the leaf(if > 0)
map: record the max singlepath value of all node
Time: O(n)
Space: O(n)
*/
class Solution {
    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        
        Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
        singlePath(root, map);
        
        //BFS to traverse every node and sum
        int res = Integer.MIN_VALUE;
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while(!q.isEmpty()){
            Queue<TreeNode> newQ = new LinkedList<TreeNode>();
            while(!q.isEmpty()){
                TreeNode node = q.poll();
                int tmp = node.val;
                if(node.left != null){
                    tmp = Math.max(tmp, tmp + map.get(node.left));
                    newQ.offer(node.left);
                }
                if(node.right != null){
                    tmp = Math.max(tmp, tmp + map.get(node.right));
                    newQ.offer(node.right);
                } 
                res = Math.max(res, tmp);
            }
            q = newQ;
        }
        return res;
    }
    private int singlePath(TreeNode node, Map<TreeNode, Integer> map){
        //get the map of <node, largest single path sum with node itself>
        if(node == null) return 0;
        int res = node.val + Math.max(0, Math.max(singlePath(node.left, map), singlePath(node.right, map)));
        map.put(node, res);
        
        //System.out.println(node.val +" "+ res);
        return res;
    }
}