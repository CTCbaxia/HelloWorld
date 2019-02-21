/*
MEDIUM
990. Satisfiability of Equality Equations
*/
/*
Union Find
0 initial: make own parent
1 pass: make ==, build parent relationship
2 pass: check !=

Time: O(n): amortized O(1) + First pass all equations O(N) + Second pass all inequations O(N)
Space: O(n)
*/
class Solution {
    public boolean equationsPossible(String[] equations) {
        int[] parent = new int[26];
        for(int i = 0; i < 26; i++){
            parent[i] = i;
        }
        for(String s : equations){//first pass for ==
            if(s.charAt(1) == '='){
                int p1 = find(parent, s.charAt(0) - 'a');
                int p2 = find(parent, s.charAt(3) - 'a');
                parent[p1] = p2;
            }
        }
        for(String s : equations){//second pass for !=
            if(s.charAt(1) == '!'){
                int p1 = find(parent, s.charAt(0) - 'a');
                int p2 = find(parent, s.charAt(3) - 'a');
                if(p1 == p2) return false;
            }
        }
        
        return true;
    }
    private int find(int[] parent, int c){
        while(parent[c] != c){
            c = parent[c];
        }
        return c;
    }
}




/*
Union Find

Time: O(n)
Space: O(n)
*/
class Solution {
    public boolean equationsPossible(String[] equations) {
        int[] parent = new int[26];
        Map<Integer, List<Integer>> notParent = new HashMap<>();
        for(int i = 0; i < 26; i++){
            parent[i] = i;
        }
        for(String s : equations){
            int c1 = s.charAt(0) - 'a';
            int c2 = s.charAt(3) - 'a';
            int p1 = find(parent, c1);
            int p2 = find(parent, c2);
            
            if(s.substring(1,3).equals("==")){
                parent[p2] = p1;
            }else{
                if(!notParent.containsKey(c1)) notParent.put(c1, new ArrayList<>());
                notParent.get(c1).add(c2);
            }
        }
        
        for(Map.Entry<Integer, List<Integer>> entry : notParent.entrySet()){
            int c1 = entry.getKey();
            List<Integer> contrast = entry.getValue();
            for(int c2 : contrast){
                if(find(parent, c1) == find(parent, c2)) return false;
            }
        }
        return true;
    }
    private int find(int[] parent, int c){
        if(parent[c] != c) return find(parent, parent[c]);
        else return c;
    }
}