/*
MEDIUM
987. Vertical Order Traversal of a Binary Tree
*/
/*
TreeMap< X, TreeMap< Y, PriorityQueue<Value>>>
XY都要求顺序输出，对于相同 XY 的 values，要求数字从小到大输出

Time: O(nlogn*logn*logn)
Space: O(n)

 */
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        //put all node.val into the TreeMap<x, <y, value>>
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        
        //traverse the tree
        dfs(root, map, 0, 0);
        
        //put into result
        List<List<Integer>> res = new ArrayList<>();
        for(Map<Integer, PriorityQueue<Integer>> ys : map.values()){
            res.add(new ArrayList<Integer>());
            for(PriorityQueue<Integer> values: ys.values()){
                while(!values.isEmpty()){
                    res.get(res.size() - 1).add(values.poll());
                }
            }
        }

        return res;
    }
    private void dfs(TreeNode node, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map, int x, int y){
        if(node == null) return;
        
        dfs(node.left, map, x - 1, y + 1);
        
        if(!map.containsKey(x)) map.put(x, new TreeMap<>());
        if(!map.get(x).containsKey(y)) map.get(x).put(y, new PriorityQueue<>());
        map.get(x).get(y).offer(node.val);
        
        dfs(node.right, map, x + 1, y + 1);
        
    }
}





/*
TreeMap< X, Map< Y, PriorityQueue<Value>>>


 */
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        //put all node.val into the TreeMap<x, <y, value>>
        TreeMap<Integer, Map<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();//❌这里 y 也要用 treemap，因为得保证从小到大
        
        //traverse the tree
        dfs(root, map, 0, 0);
        
        //put into result
        List<List<Integer>> res = new ArrayList<>();
        for(Map<Integer, PriorityQueue<Integer>> ys : map.values()){
        
            res.add(new ArrayList<Integer>());
            for(PriorityQueue<Integer> values: ys.values()){
                res.get(res.size() - 1).addAll(values);//❌ 不能这样 add pq。只有 pq.poll() 的操作是按顺序输出的
                System.out.println(values);//这样 print 出来的顺序是按照输入时候的顺序
            }
        }
        return res;
    }
    private void dfs(TreeNode node, TreeMap<Integer, Map<Integer, PriorityQueue<Integer>>> map, int x, int y){
        if(node == null) return;
        
        dfs(node.left, map, x - 1, y - 1);//❌ 为了顺序是从小到大，这里改为 y + 1
        
        if(!map.containsKey(x)) map.put(x, new TreeMap<>());
        if(!map.get(x).containsKey(y)) map.get(x).put(y, new PriorityQueue<>());
        map.get(x).get(y).offer(node.val);
        
        dfs(node.right, map, x + 1, y - 1);//❌ 为了顺序是从小到大，这里改为 y + 1
        
    }
}