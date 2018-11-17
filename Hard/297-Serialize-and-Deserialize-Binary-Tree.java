/*
HARD
297. Serialize and Deserialize Binary Tree

TIME: 
RESULT: 
NOTES: 
*/
/*
1. Serialize: Queue
Level order traverse the tree(also put null to queue), use # for null
    1
   / \
  2   3
     / \
    4   5
    
String: 1 2 3 # # 4 5 # # # # 

Time: O(n)
Space: O(n)

2. Deserialize: Queue
put node(that has value) to queue, it can be parent, and should have two children(## also count)
every parent pop from queue should have two children, 
so assign children to parent one by one

Time: O(n)
Space: O(n)
*/
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //BFS level traverse the tree
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(node == null){
                sb.append("# ");
                continue;
            } 
            sb.append(node.val).append(" ");
            q.offer(node.left);
            q.offer(node.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<TreeNode> q = new LinkedList<>();
        String[] nodes = data.split(" ");
        if(nodes[0].equals("#")) return null;
        
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        q.offer(root);
        int i = 1;
        while(i < nodes.length){
            TreeNode parent = q.poll();//poll out a parent from queue to accept children(help i move)
            if(!nodes[i].equals("#")){
                TreeNode left = new TreeNode(Integer.parseInt(nodes[i]));
                q.offer(left);
                parent.left = left;
            }
            if(!nodes[++i].equals("#")){
                TreeNode right = new TreeNode(Integer.parseInt(nodes[i]));
                q.offer(right);
                parent.right = right;
            }
            i++;
        }
        return root;
    }

}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));