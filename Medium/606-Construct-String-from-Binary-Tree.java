/*
MEDIUM
606. Construct String from Binary Tree
https://leetcode.com/problems/construct-string-from-binary-tree/description/

TIME: 0808 - 1h
RESULT: 97% - 9ms
NOTES: 
1. StringBuilder 比 s = s + anotherstring 要快很多；

*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public String tree2str(TreeNode t) {
        if(t == null) return "";
        StringBuilder sb = new StringBuilder();
        strHelper(t, sb);
        return sb.toString();
    }
    private void strHelper(TreeNode t, StringBuilder sb){
        if(t != null){
            sb.append(t.val);
            if(t.left != null || t.right != null){
                sb.append("(");
                strHelper(t.left, sb);
                sb.append(")");
                if(t.right != null){
                    sb.append("(");
                    strHelper(t.right, sb);
                    sb.append(")");                
                }
            }            
        } 
        return;
    }
}
/*
SOLUTION 0':
TIME: 0808 - 1h
RESULT: 97% - 9ms
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public String tree2str(TreeNode t) {
        if(t == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(t.val);
        strHelper(t, sb);
        return sb.toString();
    }
    private void strHelper(TreeNode t, StringBuilder sb){//条件可以简化
        if(t.left != null && t.right != null){
            sb.append("(");
            sb.append(t.left.val);
            strHelper(t.left, sb);
            sb.append(")");

            sb.append("(");
            sb.append(t.right.val);
            strHelper(t.right, sb);
            sb.append(")");
        }else if(t.left != null && t.right == null){
            sb.append("(");
            sb.append(t.left.val);
            strHelper(t.left, sb);
            sb.append(")");
            
        }else if(t.left == null && t.right != null){
            sb.append("()");
            sb.append("(");
            sb.append(t.right.val);
            strHelper(t.right, sb);
            sb.append(")");
        }else if(t.left == null && t.right == null){
            
        }
        return;
        
    }
}

/*
SOLUTION 0:
TIME: 0808 - 1h
RESULT: 3% - 127ms
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public String tree2str(TreeNode t) {
        if(t == null) return "";
        String result = Integer.toString(t.val);
        result = strHelper(t, result);
        return result;
    }
    private String strHelper(TreeNode t, String s){
        if(t.left != null && t.right != null){
            s = s + "(" + t.left.val;
            s = strHelper(t.left, s);
            s = s + ")";
            
            s = s + "(" + t.right.val;
            s = strHelper(t.right, s);
            s = s + ")";
        }else if(t.left != null && t.right == null){
            s = s + "(" + t.left.val;
            s = strHelper(t.left, s);
            s = s + ")";
            
        }else if(t.left == null && t.right != null){
            s = s + "()";
            s = s + "(" + t.right.val;
            s = strHelper(t.right, s);
            s = s + ")";
        }else if(t.left == null && t.right == null){
            
        }
        return s;
        
    }
}