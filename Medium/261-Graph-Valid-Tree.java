/*
M
261. Graph Valid Tree
*/
/*
Union Find

Valid Tree
1. Do not have circle
2. Connected

Time: O(n)
Space: O(n)
*/
class Solution {
    public boolean validTree(int n, int[][] edges) {
        //initialization
        Map<Integer, Integer> parent = new HashMap<>();
        for(int i = 0; i < n; i++){
            parent.put(i, i);
        }
        int count = n;
        for(int[] e : edges){
            int p1 = find(parent, e[0]), p2 = find(parent, e[1]);
            if(p1 == p2) return false;//there is a circle
            else{
                count--;//merged one set
                parent.put(p2, p1);//union
            }
        }
        return count == 1;
    }
    private int find(Map<Integer, Integer> parent, int p){
        if(parent.get(p) == p) return p;
        else{
            parent.put(p, find(parent, parent.get(p)));//update p's parent to the terminal parent
            return parent.get(p);
        }
    }
}
