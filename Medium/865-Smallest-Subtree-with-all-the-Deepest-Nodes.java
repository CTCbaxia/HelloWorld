/*
MEDIUM
865. Smallest Subtree with all the Deepest Nodes

TIME: 1105
RESULT: 
NOTES:

*/
/*
构造 nodeLevel 类，存可以到达所有 level 深度的最低 node
for a node, 
if left and right can reach the same level, then return nodeLevel(node, max level = left/right level)
if left can reach far more, then return nodeLevel(left.node, max level = left level)
if right can reach far more, then return nodeLevel(right.node, max level = right level)
返回某个 node 可以到达的最大深度，以及 reach 那个深度的 node

Time: O(n)
Space: O(d) //递归深度d or logn
*/
class Solution {
    public class nodeLevel{
        TreeNode node;
        int level;
        public nodeLevel(TreeNode _node, int _level){
            node = _node;
            level = _level;
        }
    }
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return depth(root).node;
    }
    private nodeLevel depth(TreeNode node){
        if(node == null) return new nodeLevel(null, 0);
        nodeLevel left = depth(node.left);
        nodeLevel right = depth(node.right);
        
        int level = 1 + Math.max(left.level, right.level);//返回值依然是 1 + Math.max(left, right)
        
        if(left.level == right.level) return new nodeLevel(node, level);//但是要选择最低点
        else if(left.level > right.level) return new nodeLevel(left.node, level);
        else return new nodeLevel(right.node, level);
    }

}





/*
Recursion:
We need return node that has all deepest node in its subtree
Use global variable for result and deepest
User Helper function depth to calculate the deepest level the current node can reach

求每个node的深度（相对于root），求的过程中更新 result 
相对于 root 的 Depth + 全局update deep/result

Time: O(n)
Space: O(d) //递归深度d or logn
*/
class Solution {
    int deep = 0;
    TreeNode res = null;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        depth(root, 0);
        return res;
    }
    private int depth(TreeNode node, int len){//返回的是每个node相对于递归起始点，有多深
        if(node == null) return len;
        
        int left = depth(node.left, len + 1);//node 的左子树相对于 root 的深度
        int right = depth(node.right, len + 1);
        if(left == right && left >= deep){//这里必须 >= 不然会停留在最底层的某一个node，而不能更新到最深层 node 的 parent
            deep = left;
            res = node;
        }
        return Math.max(left, right);
    }
}






/*
Depth + Map<TreeNode, depth>
left = right: root
left > right: go root.left
left < right: go root.right

Time: O(n)
Space: O(n)
*/
class Solution {
    Map<TreeNode, Integer> map = new HashMap<>();//只用算一次depth了
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if(root == null) return root;

        int left = depth(root.left);
        int right = depth(root.right);
        
        if(left == right) return root;
        else if(left > right) return subtreeWithAllDeepest(root.left);
        else return subtreeWithAllDeepest(root.right);
    }
    private int depth(TreeNode node){//返回的是每个node作为root，他的subtree有多深
        if(node == null) return 0;
        if(map.containsKey(node)) return map.get(node);
        
        int left = depth(node.left);
        int right = depth(node.right);
        
        int d = 1 + Math.max(left, right);
        map.put(node, d);
        return d;
    }
}
node, depth
7 1
0 1
8 1
2 2
1 2
6 1
5 3
4 1
//❌ 
//不能这样用map存，因为存出来的值不是真的node的深度的绝对值，而是一个相对值，所以只能得到第一次算到这个node时候他的相对深度
//应该在递归调用的最初地方，存返回值
class Solution {
    Map<TreeNode, Integer> map = new HashMap<>();//只用算一次depth了
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if(root == null) return root;
        // for(Map.Entry<TreeNode, Integer> entry : map.entrySet()){
        //     System.out.println(entry.getKey().val + " "+entry.getValue());
        // }
        int left = depth(root.left, 0);
        int right = depth(root.right, 0);
        //应该在这里存map
        if(left == right) return root;
        else if(left > right) return subtreeWithAllDeepest(root.left);
        else return subtreeWithAllDeepest(root.right);
    }
    private int depth(TreeNode node, int len){//返回的是每个node相对于递归调用的起始点，有多深
        if(node == null) return len;//当发现叶子节点，返回这条path (递归起点 node 到 leaf 的深度)
        if(map.containsKey(node)) return map.get(node);
        
        int left = depth(node.left, len + 1);
        int right = depth(node.right, len + 1);
        
        int d = Math.max(left, right);
        map.put(node, d);
        return d;
    }
}
7 3
0 2
8 2
2 3
1 2
6 2
5 3
4 3










