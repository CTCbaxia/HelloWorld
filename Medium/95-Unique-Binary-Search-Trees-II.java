/*
MEDIUM
95. Unique Binary Search Trees II
https://leetcode.com/problems/minimum-genetic-mutation/description/

TIME: 0826 - 3h
RESULT: 90% - 2ms
*/

/*
SOLUTION REFERENCE:

TIME: 0826 - 3h
RESULT: 90% - 2ms

选 root
遍历 subtree
if(start == end)
    直到 subTree(start, end) 结束的时候，List.add(TreeNode)
if(start < end)
    left = subTree(start, end);
    right = subTree(start, end);
    for(TreeNode tl : left)
        for(TreeNode tr : right)
            root.left = tl;
            root.right = tr;
            res.add(root);

*/
class Solution {
    public List<TreeNode> generateTrees(int n) {

        return genSubTree(1, n);
    }   
    private List<TreeNode> genSubTree(int start, int end){
        List<TreeNode> list = new ArrayList<TreeNode>();
        if(start == end){// the leaf
            list.add(new TreeNode(start));
            return list;
        }else if(start > end){
            list.add(null);//null 也是个 TreeNode
            return list;            
        }else{
            for(int i = start; i <= end; i++){
                List<TreeNode> left = genSubTree(start, i - 1);
                List<TreeNode> right = genSubTree(i + 1, end);
                
                for(TreeNode tl : left){
                    for(TreeNode tr : right){
                        TreeNode root = new TreeNode(i);
                        root.left = tl;
                        root.right = tr;
                        list.add(root);
                    }
                }
            }
            return list;
        }
    }
}