/*
EASY
257. Binary Tree Paths

RESULT: 99% - 12ms
NOTES: 
Backtracking
当问题有很多回溯的时候，可以用 string 相连，这样减少了很多 delete 操作
*/
/* 
Time: O(n)
Space: O(h)
RESULT: 94% - 10ms
*/
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        if(root != null) findPath(root, "", result);
        return result;
    }
    private void findPath(TreeNode node, String s, List<String> result){
        if(node.left == null && node.right == null) result.add(s + node.val);
        if(node.left != null) findPath(node.left, s + node.val + "->", result);
        if(node.right != null) findPath(node.right, s + node.val + "->", result);
        return;
    }
}
/* 
每次检查这个 node ？ null
如果不等于就 add

再看 left right 满足情况，都不满足就加入结果。有满足就继续 recursive

Time: O(n)
Space: O(h)
RESULT: 94% - 10ms
*/
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        findPath(root, new StringBuilder(), result);
        return result;
    }
    private void findPath(TreeNode node, StringBuilder sb, List<String> result){
        if(node != null){
            int len = sb.length();
            sb.append(node.val);
            //if no children
            if(node.left == null && node.right == null) result.add(sb.toString());
            
            //if with children
            sb.append("->");
            if(node.left != null) findPath(node.left, sb, result);
            if(node.right != null) findPath(node.right, sb, result);
            sb.setLength(len);
        }

        return;
    }
}
//其实不需要每次减少添加单词的长度，直接记下之前的长度就好
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        findPath(root, new StringBuilder(), result);
        return result;
    }
    private void findPath(TreeNode node, StringBuilder sb, List<String> result){
        if(node != null){
            sb.append(node.val).append("->");
            String s = String.valueOf(node.val)+"->";
            if(node.left != null) findPath(node.left, sb, result);
            if(node.right != null) findPath(node.right, sb, result);
            if(node.left == null && node.right == null){
                String str = sb.toString();
                result.add(str.substring(0, str.length() - 2));
            }
            sb.delete(sb.length() - s.length(), sb.length());
        }

        return;
    }
}