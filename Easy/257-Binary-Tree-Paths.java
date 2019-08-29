/*
EASY
257. Binary Tree Paths

RESULT: 99% - 12ms
NOTES: 
Backtracking
当问题有很多回溯的时候，可以用 string 相连，这样减少了很多 delete 操作
*/
/*
String path, no need backtracking, only dfs
Time: O(n)
Space: O(logn) -- stack
*/
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, "", res);
        return res;
    }
    private void dfs(TreeNode node, String path, List<String> res){//backtracking
        if(node == null) return;
        path += node.val;
        if(node.left == null && node.right == null){
            res.add(path);
        }else{
            path += "->";
            dfs(node.left, path, res);
            dfs(node.right, path, res);
        }
    }
}


/* 
DFS(backtracking) + String Prefix
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
DFS(backtracking) + StringBuilder + sb.setLength(len)
helper 函数里面 node != null
每次先记录未添加 node.val 时候的长度
然后
    1. 检查是否为叶子
    2. 继续 dfs 还有的path
    3. 移除这个node：sb.setLength(len)


Time: O(n)
Space: O(h)
RESULT: 94% - 10ms
*/
class Solution {
    public List<String> binaryTreePaths(TreeNode root){
        List<String> result = new ArrayList<>();
        dfs(root, result, new StringBuilder());
        return result;
    }
    private void dfs(TreeNode node, List<String> result, StringBuilder sb){
        if(node == null) return;
        int len = sb.length();

        sb.append(node.val);

        if(node.left == null && node.right == null){
            result.add(sb.toString());
        }else{
            sb.append("->");
            dfs(node.left, result, sb);
            dfs(node.right, result, sb);
        }
        sb.setLength(len);
        return;
    }

}


class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        if(root == null) return result;

        findPath(root, new StringBuilder(), result);
        return result;
    }
    private void findPath(TreeNode node, StringBuilder sb, List<String> result){
        int len = sb.length();
        sb.append(node.val);
        //if no children
        if(node.left == null && node.right == null) result.add(sb.toString());
        
        //if with children
        sb.append("->");
        if(node.left != null) findPath(node.left, sb, result);
        if(node.right != null) findPath(node.right, sb, result);
        sb.setLength(len);

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