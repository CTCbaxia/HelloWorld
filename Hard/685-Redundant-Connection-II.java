/*
H
685. Redundant Connection II
*/
/*
Union Find
case1: 2 parents no circle
case2: 2 parents with circle
case3: 1 parent with circle

       1
      / \
     *   *
    2 * - 3
   /       \
  *         *
 4          5


       1 *---
      / \   |
     *   *  |
    2     3 |
   /       \|
  *         *
 4          5

           1
          / \
         *   *
  ----* 2     3
  |    /       \
  |   *         *
  |  4          5
  | /
   *
  6

1. see if there is multi parent, remove one parent and candidate parents edge
2. check if there is circle

Time: O(n)
Space: O(n)
*/
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        
        //近亲 union find
        int[] parent = new int[n + 1];
        int[] cand1 = null, cand2 = null;
        for(int[] e : edges){
            if(parent[e[1]] != 0){//e[1] already has one parent
                cand1 = new int[]{parent[e[1]], e[1]};
                cand2 = new int[]{e[0], e[1]};//try remove cand2 because it shows later
                e[0] = -1;//make this cand2 invalid to test
                break;
            }else{
                parent[e[1]] = e[0];
            }
        }
        //reset parent
        for(int i = 1; i <= n; i++) parent[i] = i;

        //正式 union find to find if there is a circle
        for(int[] e : edges){
            if(e[0] == -1) continue;//this edge is the removed cand2
            int p1 = find(e[0], parent);
            int p2 = find(e[1], parent);
            if(p1 == p2){//find circle
                if(cand2 == null) return e;//no multi parent, return current edge
                else return cand1;//has multi parent, previously removed the wrong one
            }else{
                parent[p2] = p1;
            }
        }
        return cand2;
        
    }
    private int find(int n, int[] parent){
        if(parent[n] == n) return n;
        parent[n] = find(parent[n], parent);
        return parent[n];
    }

}






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
