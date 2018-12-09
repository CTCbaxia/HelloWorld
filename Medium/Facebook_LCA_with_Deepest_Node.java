import java.io.*;
import java.util.*;
import java.math.*;
/*
Get the LCA of the deepest nodes in a binary tree
   1
  2  3
    5  6 
return 3
    
        1  
    2     3
  4      5 6
return 1
*/
class Facebook_LCA_with_Deepest_Node {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		//root.left.left = new TreeNode(4);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(6);
  		/*
  		        1  
		    2     3
		         5 6
		*/
		System.out.println(LCA(root));
	}
	static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int _val){
            val = _val;
        }
    }
	static class nodeDepth{
	    int d;
	    TreeNode node;
	    public nodeDepth(int _d, TreeNode _node){
	        d = _d;
	        node = _node;
	    }
	}   
	public static int LCA(TreeNode root){
	    return depth(root).node.val;
	}

	private static nodeDepth depth(TreeNode node){
	    if(node == null) return new nodeDepth(0, node);
	    nodeDepth left = depth(node.left);
	    nodeDepth right = depth(node.right);
	    if(left.d == right.d){
	        return new nodeDepth(left.d + 1, node);
	    }else{
	        if(left.d > right.d) return new nodeDepth(left.d + 1, left.node);
	        else return new nodeDepth(right.d + 1, right.node); 
	    }
	}

}