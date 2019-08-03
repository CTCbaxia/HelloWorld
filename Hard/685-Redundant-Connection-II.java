/*
H
685. Redundant Connection II
*/
/*
Union Find + Find Circle
2 invalid cases
1) one node has two parents --> remove one candidate edge, try union find to see if there is only one group
2) a directed circle --> remove the last edge

Time: O(n) Only traverse the tree three times
Space: O(n)
*/
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        Map<Integer, Integer> parents = new HashMap<>();

        // see if there is multi parent
        int[] candidate1 = null, candidate2 = null;
        for(int[] edge : edges){
            if(parents.containsKey(edge[1]) && parents.get(edge[1]) != edge[1]){//it has already been assigned another parent
                candidate1 = new int[]{parents.get(edge[1]), edge[1]};
                candidate2 = new int[]{edge[0], edge[1]};//make this edge invalid but candidate it
                edge[0] = -1;//mark remove this edge
                break;
            }else{
                parents.put(edge[1], edge[0]);
            }
        }
        
        // initialization for union find, make everyone as its own parent
        for(int i = 1; i <= edges.length; i++){
            parents.put(i, i);
        }
        // if there is a valid tree, the removed one works good
        // else if there is a circle, then check if you previously have two candidate, 
        //      if so, the candidate2 should be removed
        //      else, the curent candidate should be removed
        // else remove candidate2
        
        // loop each edge
        int count = parents.size();
        for(int[] edge : edges){
            if(edge[0] == -1) continue;
            int u = edge[0], v = edge[1], pu = find(u, parents);
            if(pu == v){//在添加这个 edge 之前就要check，不然死循环。there is a circle
                if(candidate2 != null) return candidate1;
                else return edge;
            }
            parents.put(v, pu);
            count--;
        }
        if(count > 1) return candidate1;//disjoint set, should return the other one
        else return candidate2;//the tree is valid
        
        
    }

    private int find(int p, Map<Integer, Integer> parents){
        if(parents.get(p) != p) parents.put(p, find(parents.get(p), parents));
        return parents.get(p);
    }
}
