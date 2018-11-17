/*
MEDIUM
449. Serialize and Deserialize BST

TIME: 
RESULT: 
NOTES:

*/
/*
BST 的 preorder traversal 可以直接帮助区分左右子树({less}, root, {more})
encode: 
	preorder traversal the tree

decode:
	root left left left right right

	left < root
	right > root
	so we can divide subtrees and root


Time: 
	1. encode: O(n)
	2. decode: O(n^2)   T(n) = T(n - 1) + n  unbalanced  |  T(n) = 2T(n/2) + n/2  -> O(nlogn)
Space:
String: O（2n)
 */
public class Codec {
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {//construct the tree using preorder traversal.
        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append(" ");
        sb.append(serialize(root.left));
        sb.append(serialize(root.right));
        return sb.toString();//preorder: 2 1 3
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == "") return null;//if data = "", str 会有一个空element，到nums会出错
        
        String[] str = data.split(" ");
        int[] nums = new int[str.length];
        
        for(int i = 0; i < str.length; i++){//transform string[] to int[]
            nums[i] = Integer.parseInt(str[i]);
        }
        
        return getNode(nums, 0, nums.length - 1);
    }
    private TreeNode getNode(int[] nums, int start, int end){
        if(start > end) return null;
        TreeNode node = new TreeNode(nums[start]);
        
        int i = start + 1;
        while(i <= end && nums[i] < nums[start]) i++;
        node.left = getNode(nums, start + 1, i - 1);
        node.right = getNode(nums, i, end);
        
        return node;
    }
}





















/*
encode the tree using () for every subtree
((1)2(3))
((2(3))4(5))

and decode string using the ((left) root (right)) for the pattern

Time: 
	1. encode: O(n)
	2. decode: O(n^2)   T(n) = T(n - 1) + n  unbalanced  |  T(n) = 2T(n/2) + n/2  -> O(nlogn)
Space:
String: O(3n) 每个数字两个括号
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";
        
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(serialize(root.left)).append(root.val).append(serialize(root.right)).append(")");
        return sb.toString();
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0) return null;
        String str = data.substring(1, data.length() - 1);//remove outer ()
        
        TreeNode root = new TreeNode(0);
        
        //left
        int start = 0;
        int end = getSubtree(str, start);
        root.left = deserialize(str.substring(start, end));
        
        //root.val
        int i = end;
        int n = 0;
        while(i < str.length() && Character.isDigit(str.charAt(i))){
            n = 10*n + str.charAt(i++) - '0';
        }
        root.val = n;
        
        //right
        start = i;
        end = getSubtree(str, start);
        root.right = deserialize(str.substring(start, end));
        
        return root;
        
    }
    private int getSubtree(String s, int start){//get "(...)"
        
        int i = start;
        if(i < s.length() && s.charAt(i) == '('){
            int count = 1;
            while(++i < s.length() && count != 0){
                if(s.charAt(i) == '(') count++;
                else if(s.charAt(i) == ')') count--;
            }
        }
        return i;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));