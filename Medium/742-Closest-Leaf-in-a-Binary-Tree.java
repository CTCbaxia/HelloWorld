/*
M
742. Closest Leaf in a Binary Tree
*/
/*
DFS (Recursion)
when looping the tree, update the 
1) node's min depth 
2) distance to the key
3) the res node value


Time: O(n)
Space: O(1)
*/
class Solution {
    public class NodeInfo{
        int minDepth;
        int disToKey;
        int leaf;
        public NodeInfo(int _minDepth, int _disToKey, int _leaf){
            minDepth = _minDepth;
            disToKey = _disToKey;
            leaf = _leaf;
        }
    }
    
    int minDis = Integer.MAX_VALUE;
    int resLeafValue = 0;
    public int findClosestLeaf(TreeNode root, int k) {
        dfs(root, k);
        return resLeafValue;
    }
    private NodeInfo dfs(TreeNode node, int k){
        if(node == null) return new NodeInfo(0, -1, 0);
        
        NodeInfo left = dfs(node.left, k);
        NodeInfo right = dfs(node.right, k);
        
        int minDepth = 1;//if this node is the leaf
        int leaf = node.val;
        if(left.minDepth != 0 && right.minDepth != 0){
            minDepth = (left.minDepth < right.minDepth ?  left.minDepth : right.minDepth) + 1;
            leaf = left.minDepth < right.minDepth ? left.leaf : right.leaf;
        }else if(left.minDepth != 0){
            minDepth = left.minDepth + 1;
            leaf = left.leaf;
        }else if(right.minDepth != 0){
            minDepth = right.minDepth + 1;
            leaf = right.leaf;
        }
        int disToKey = -1;// if no key in its child tree
        if(node.val == k){
            disToKey = 1;
        }else if(left.disToKey != -1){
            disToKey = left.disToKey + 1;
        }else if(right.disToKey != -1){
            disToKey = right.disToKey + 1;
        }
        
        if(disToKey != -1){
            int curDis = minDepth + disToKey - 1;
            if(curDis < minDis){
                minDis = curDis;
                resLeafValue = leaf;
            }
        }
        
        return new NodeInfo(minDepth, disToKey, leaf);
    }
}





/*
DFS (Recursion)
when looping the tree, update the 
1) node's min depth 
2) distance to the key
3) the res node value


Time: O(n)
Space: O(1)
*/
class Solution {
    int minDis = Integer.MAX_VALUE;
    int resLeafValue = 0;
    public int findClosestLeaf(TreeNode root, int k) {
        dfs(root, k);
        return resLeafValue;
    }
    // int[]{min depth to leaf, dis to the key}
    private int[] dfs(TreeNode node, int k){
        if(node == null) return new int[]{0, -1, 0};
        
        int[] left = dfs(node.left, k);
        int[] right = dfs(node.right, k);
        
        int minDepth = 1;//if this is a node, then at least the depth is 1
        int leafValue = node.val;
        if(left[0] != 0 && right[0] != 0){
            minDepth += left[0] < right[0] ? left[0] : right[0];
            leafValue = left[0] < right[0] ? left[2] : right[2];
        }else if(left[0] != 0){// if there is one child is null, then the depth should depend on its child
            minDepth += left[0];
            leafValue = left[2];
        }else if(right[0] != 0){
            minDepth += right[0];
            leafValue = right[2];
        }//else left[0] && right[0], minDepth should be 1
        
        int DisToKey = -1;
        if(node.val == k){
            DisToKey = 1;
        }else if(left[1] != -1){//if key is in left subtree
            DisToKey = left[1] + 1;
        }else if(right[1] != -1){//if key is in right subtree
            DisToKey = right[1] + 1;
        }
        if(DisToKey != -1){
            int curDis = minDepth + DisToKey - 1;
            if(curDis < minDis){
                minDis = curDis;
                resLeafValue = leafValue;
            }
        } 
        // System.out.println(node.val + " "+minDepth + " "+DisToKey);
        return new int[]{minDepth, DisToKey, leafValue};
    }
}