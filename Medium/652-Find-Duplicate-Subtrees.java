/*
652. Find Duplicate Subtrees
*/
/*
Serialize every node of the tree -- O(n)
Add to result when find duplicate == 1 -- O(n) just check when traverse the tree

Time: O(n) -- one traverse
Space: O(n)
*/
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> map = new HashMap<>();
        List<TreeNode> result = new ArrayList<>();
        encode(root, map, result);
        
        return result;
    }
    private String encode(TreeNode node, Map<String, Integer> map, List<TreeNode> res){
        if(node == null) return "# ";
        
        String left = encode(node.left, map, res);
        String right = encode(node.right, map, res);
        String s = Integer.toString(node.val) + " " + left  + right;
        
        if(map.getOrDefault(s, 0) == 1) res.add(node);
        map.put(s, map.getOrDefault(s, 0) + 1);
        
        return s;
    }
}



/*
Serialize every node of the tree -- O(n)
Compare every two nodes of the tree -- O(n) just loop once and check same

Time: O(n) -- two traverse
Space: O(n)
*/
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<TreeNode, String> map = new HashMap<>();
        encode(root, map);//serialize the subtrees for comparation 
        
        //check if duplicate
        Set<String> counted = new HashSet<>();
        Set<String> all = new HashSet<>();
        List<TreeNode> result = new ArrayList<>();
        for(Map.Entry<TreeNode, String> entry : map.entrySet()){
            String s = entry.getValue();
            if(all.contains(s) && !counted.contains(s)){//haven't put in result
                result.add(entry.getKey());
                counted.add(s);
            }
            all.add(s);
        }
        return result;
    }
    private String encode(TreeNode node, Map<TreeNode, String> map){
        if(node == null) return "# ";
        
        String left = encode(node.left, map);
        String right = encode(node.right, map);
        String s = Integer.toString(node.val) + " " + left  + right;    
        map.put(node, s);
        return s;
    }
}








/*
不够优化
Serialize every node of the tree -- O(n)
Compare every two nodes of the tree -- O(n^2)

Time: O(n^2)
Space: O(n)
*/
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<TreeNode, String> map = new HashMap<>();
        encode(root, map);
        
        Set<String> counted = new HashSet<>();
        List<TreeNode> result = new ArrayList<>();
        for(Map.Entry<TreeNode, String> entry1 : map.entrySet()){
            if(counted.contains(entry1.getValue())) continue;
            for(Map.Entry<TreeNode, String> entry2 : map.entrySet()){
                if(entry1 == entry2) continue;
                if(entry1.getValue().equals(entry2.getValue())){
                    result.add(entry1.getKey());
                    counted.add(entry1.getValue());
                    break;
                }
            }
        }
        return result;
    }
    private String encode(TreeNode node, Map<TreeNode, String> map){
        if(node == null) return "# ";
        
        String left = encode(node.left, map);
        String right = encode(node.right, map);
        String s = Integer.toString(node.val) + " " + left  + right;    
        map.put(node, s);
        return s;
    }
}



/*
isSame 来当场对比每个 node --- 不好做
*/
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<Integer, List<TreeNode>> map = new HashMap<>();
        inorder(map, root);
        
        List<TreeNode> result = new ArrayList<>();
        Set<TreeNode> visited = new HashSet<>();
        for(Map.Entry<Integer, List<TreeNode>> entry : map.entrySet()){
            List<TreeNode> list = entry.getValue();
            for(int i = 0; i < list.size(); i++){
                if(visited.contains(list.get(i))) continue;
                for(int j = i + 1; j < list.size(); j++){
                    if(isSame(list.get(i), list.get(j))){
                        result.add(list.get(i));
                        visited.add(list.get(i));
                        visited.add(list.get(j));
                        break;
                    }
                }
            }
            
        }
        return result;
    }
    private boolean isSame(TreeNode n1, TreeNode n2){
        if(n1 == null) return n2 == null;
        else if(n2 == null) return n1 == null;
        return n1.val == n2.val && isSame(n1.left, n2.left) && isSame(n1.right, n2.right);
    }
    private void inorder(Map<Integer, List<TreeNode>> map, TreeNode node){
        if(node == null) return;
        inorder(map, node.left);
        
        if(!map.containsKey(node.val)) map.put(node.val, new ArrayList<>());
        map.get(node.val).add(node);
        
        inorder(map, node.right);
    }
}


