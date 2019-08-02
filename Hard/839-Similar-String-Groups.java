/*
H
839. Similar String Groups
*/
/*
Union Find
Since all are anagrams, check each character and there should be exactly 2 diff

Tip: to speed up
1) update the parent when doing find (update on the go, so no need to traverse the whole list again)
2) IF all element are DISTINCT: update (count--) the num of disjoint group every time you find a connection
to make element distinct, using their index to represent the element

Time: O(n^2 * len)?
Space: O(n)
*/
class Solution {
    public int numSimilarGroups(String[] A) {
        // initialize parent map
        Map<Integer, Integer> parent = new HashMap<>();
        for(int i = 0; i < A.length; i++){
            parent.put(i,i);
        }
        // union find
        int count = A.length;
        for(int i = 0; i < A.length; i++){
            for(int j = i + 1; j < A.length; j++){
                if(isSimilar(A[i], A[j])){
                    int p1 = find(i, parent);
                    int p2 = find(j, parent);
                    if(p1 != p2){// can union two groups 
                        parent.put(p1, p2);
                        count--;
                    } 
                }  
            }
        }

        return count;
    }
    private int find(int i, Map<Integer, Integer> parent){
        if(parent.get(i) != i) parent.put(i, find(parent.get(i), parent));//update the union find
        return parent.get(i);
    }
    private boolean isSimilar(String a, String b){
        int n = a.length();
        int count = 0;
        for(int i = 0; i < n; i++){
            if(a.charAt(i) != b.charAt(i)){
                count++;
            }
        }
        return count == 2 || count == 0;
    }
}

/*
Union Find
Since all are anagrams, check each character and there should be exactly 2 diff

Time: O(n^2)?
Space: O(n)
*/
class Solution {
    public int numSimilarGroups(String[] A) {
        // initialize parent map
        Map<String, String> parent = new HashMap<>();
        for(String s : A){
            parent.put(s,s);
        }
        // union find
        for(int i = 0; i < A.length; i++){
            for(int j = i + 1; j < A.length; j++){
                if(isSimilar(A[i], A[j])){
                    String p1 = find(A[i], parent);
                    String p2 = find(A[j], parent);
                    parent.put(p1, p2);
                }  
            }
        }
        Set<String> set = new HashSet<>();
        for(Map.Entry<String, String> entry : parent.entrySet()){
            set.add(find(entry.getKey(), parent));//add each string's parent into the set
        }
        return set.size();
    }
    private String find(String s, Map<String, String> parent){
        if(!parent.get(s).equals(s)) parent.put(s, find(parent.get(s), parent));
        return parent.get(s);
    }
    private boolean isSimilar(String a, String b){
        int n = a.length();
        int count = 0;
        for(int i = 0; i < n; i++){
            if(a.charAt(i) != b.charAt(i)){
                count++;
            }
        }
        return count == 2;
    }
}
