/*
MEDIUM
113. Path Sum II

TIME: 
RESULT: 
NOTES:

*/
/*
DFS: Backtracking + recursive
DFS the tree for every path 
and update the target we need to reach for the rest of the treenode in that path
need to remove (backtracking)

Time: O(n) we only travel every node once
Space: O(1)
*/
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if(node == null) return result;
        
        findPath(root, sum, new ArrayList<>(), result);
        return result;
    }
    private void findPath(TreeNode node, int target, List<Integer> res, List<List<Integer>> result){
        res.add(node.val);
        target = target - node.val;//update new target
        
        if(node.left == null && node.right == null && target == 0){
            result.add(new ArrayList<Integer>(res));
        } 
        if(node.left != null) findPath(node.left, target, res, result);//find path to the left
        if(node.right != null) findPath(node.right, target, res, result);//find path to the right
        res.remove(res.size() - 1);
        return;
    }
}


//we cannot do DFS using stack because we will travel root only once, so we can only get one part of the tree 
//(we will pop up the root and go to the right part without the root value in the result)