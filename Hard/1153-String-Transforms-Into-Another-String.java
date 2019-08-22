/*
H
1153. String Transforms Into Another String
*/
/*
Graph - 
1. see if there is a conflict in matching
2. see if there is a circle, if there is, then there must be another char that is not used.

Time: O(n)
Space: O(n)
*/
class Solution {
    public boolean canConvert(String str1, String str2) {
        if(str1.length() != str2.length()) return false;
        
        int n = str1.length();
        
        // build map
        Set<Character> used = new HashSet<Character>();
        Map<Character, Character> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            if(c1 != c2){
                if(map.containsKey(c1) && map.get(c1) != c2) return false;
                else map.put(c1, c2);
            }
            used.add(c2);
        }
        //find circle: if there is a circle, there must be one more unused char in str2
        int numOfUsed = used.size();
        Set<Character> visited = new HashSet<>();
        for(Map.Entry<Character, Character> entry : map.entrySet()){
            if(visited.contains(entry.getKey())) continue;
            Set<Character> set = new HashSet<>();
            if(isCircle(map, entry.getKey(), set, visited) && numOfUsed >= 26) return false;
        }
        return true;
    }
    private boolean isCircle(Map<Character, Character> map, char c, Set<Character> path, Set<Character> visited){
        if(map.containsKey(c)){
            if(path.contains(map.get(c))) return true;
            path.add(c);
            visited.add(c);
            return isCircle(map, map.get(c), path, visited);
        } 
        else return false;
    }
}
