/*
MEDIUM
314. Binary Tree Vertical Order Traversal

NOTES:
1. 第一反应是参考值。你需要记录每个节点的位置。而每个节点的位置，可以由 parent position 计算出来 (l+1 or l-1)
2. BFS 如果只是想按层遍历一边 tree，是没必要两层 while 的。找一个 queue 从头存到尾就好了，也是按层存，只是你不知道那个哪里分层而已
*/
/*
Map
Map<level, List of node values>
traverse the tree by BFS
keep updating min and max level

for(min to max) add list to result

Time: O(n)
Space: O(n)
*/
class Solution {
    public class NodeLevel{
        int level;
        TreeNode node;
        public NodeLevel(TreeNode _node, int _level){
            node = _node;
            level = _level;
        }
    }
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<NodeLevel> q = new LinkedList<>();
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        if(root != null) q.offer(new NodeLevel(root, 0));
        
        while(!q.isEmpty()){
            NodeLevel nl = q.poll();
            //add node to the map
            TreeNode node = nl.node;
            int l = nl.level;
            
            //update map
            if(!map.containsKey(l)) map.put(l, new ArrayList<Integer>());
            map.get(l).add(node.val);
            
            //update min max
            min = Math.min(min, l);
            max = Math.max(max, l);
            
            if(node.left != null) q.offer(new NodeLevel(node.left, l - 1));
            if(node.right != null) q.offer(new NodeLevel(node.right, l + 1));
        }
        for(int i = min; i <= max; i++){
            result.add(map.get(i));
        }
        return result;
    }
    
}











/*
BFS 遍历 tree
每次遍历存该 node 的 level （取决于父母的level）
将 node.val 加到 map 对应的 level key 中

Time: O(n)
Space: O(n)
*/
class Solution {
	//create a class to store (node, level)
    public class nodeLevel{
        public TreeNode node;
        public int level;
        public nodeLevel(TreeNode treenode, int nodelevel){
            node = treenode;
            level = nodelevel;
        }
    }
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null) return result;
        
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        Queue<nodeLevel> q = new LinkedList<nodeLevel>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        q.offer(new nodeLevel(root, 0));
        while(!q.isEmpty()){
            nodeLevel nl = q.poll();
            int l = nl.level;
            TreeNode node = nl.node;
            if(!map.containsKey(l)) map.put(l, new ArrayList<Integer>());
            map.get(l).add(node.val);
            min = Math.min(min, l);
            max = Math.max(max, l);
            
            if(node.left != null){
                q.offer(new nodeLevel(node.left, l - 1));
            }
            if(node.right != null){
                q.offer(new nodeLevel(node.right, l + 1));
            }
        }
        for(int i = min; i <= max; i++){
            result.add(map.get(i));
        }
        return result;
    }
}


//SOLUTION 0:
//其实可以新建一个类来存这个 node, level
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null) return result;
        
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        Map<TreeNode, Integer> level = new HashMap<TreeNode, Integer>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        q.offer(root);
        level.put(root, 0);
        while(!q.isEmpty()){
            Queue<TreeNode> newQ = new LinkedList<TreeNode>();
            while(!q.isEmpty()){
                TreeNode node = q.poll();
                int l = level.get(node);
                if(!map.containsKey(l)) map.put(l, new ArrayList<Integer>());
                map.get(l).add(node.val);
                min = Math.min(min, l);
                max = Math.max(max, l);
                
                if(node.left != null){
                    newQ.offer(node.left);
                    level.put(node.left, l - 1);
                }
                if(node.right != null){
                    newQ.offer(node.right);
                    level.put(node.right, l + 1);
                }
            }
            q = newQ;
        }
        for(int i = min; i <= max; i++){
            result.add(map.get(i));
        }
        return result;
    }
}