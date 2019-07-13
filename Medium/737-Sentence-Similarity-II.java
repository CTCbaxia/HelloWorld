/*
M
737. Sentence Similarity II
*/
/*
Union Find

n = len of words, m = len of pair
Time: O(m^2 + mn)
Space: O(m)
*/
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if(words1.length != words2.length) return false;
        
        Map<String, String> map = new HashMap<>();
        int n = words1.length;
        //union O(m^2) for worst case: travel the whole link
        for(int i = 0; i < pairs.size(); i++){
            String s1 = pairs.get(i).get(0);
            String s2 = pairs.get(i).get(1);
            String p1 = find(map, s1);//need to find both parent, otherwise may make dead loop
            String p2 = find(map, s2);
            map.put(p1, p2);
         }
         //check O(nm)
        for(int i = 0; i < n; i++){
            if(words1[i].equals(words2[i])) continue;
            if(!map.containsKey(words1[i]) || !map.containsKey(words2[i])) return false;
            String p1 = find(map, words1[i]);
            String p2 = find(map, words2[i]);
            if(!p1.equals(p2)) return false;
        }
        return true;
    }
                     
    private String find(Map<String, String> map, String source){
        if(!map.containsKey(source)) map.put(source, source);
        if(map.get(source).equals(source)) return source;
        else return find(map, map.get(source));
    }
}
/*
Union Find

n = len of words, m = len of pair
Time: O(m^2 + mn)
Space: O(m)
*/
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if(words1.length != words2.length) return false;
        
        Map<String, String> map = new HashMap<>();
        int n = words1.length;
        //union O(m^2) for worst case: travel the whole link
        for(int i = 0; i < pairs.size(); i++){
            
            String s1 = pairs.get(i).get(0);
            String s2 = pairs.get(i).get(1);
            
            if(map.containsKey(s1) && map.containsKey(s2)){//if both have link, update s2'parent point to s1
                String p1 = find(map, s1);//need to find both parent, otherwise may make dead loop
                String p2 = find(map, s2);
                map.put(p1, p2);
            }else if(map.containsKey(s1)){
                map.put(s2, s1);
            }else if(map.containsKey(s2)){
                map.put(s1, s2);
            }else{
                map.put(s1, s1);
                map.put(s2, s1);
            }
         }
         //check O(nm)
        for(int i = 0; i < n; i++){
            if(words1[i].equals(words2[i])) continue;
            if(!map.containsKey(words1[i]) || !map.containsKey(words2[i])) return false;
            String p1 = find(map, words1[i]);
            String p2 = find(map, words2[i]);
            if(!p1.equals(p2)) return false;
        }
        return true;
    }
                     
    private String find(Map<String, String> map, String source){
        if(map.get(source).equals(source)) return source;
        else return find(map, map.get(source));
    }
}
/*
DFS

n = len of words, m = len of pair
Time: O(m + n)
Space: O(m)
*/
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if(words1.length != words2.length) return false;
        Map<String, Set<String>> map = new HashMap<>();
        //build map
        for(List<String> l : pairs){
            String s1 = l.get(0);
            String s2 = l.get(1);
            map.putIfAbsent(s1, new HashSet<>());
            map.putIfAbsent(s2, new HashSet<>());
            map.get(s1).add(s2);
            map.get(s2).add(s1);
        }
        //do dfs
        int n = words1.length;
        for(int i = 0; i < n; i++){
            if(words1[i].equals(words2[i])) continue;
            if(!map.containsKey(words1[i])) return false;
            if(!isSimilar(map, words1[i], words2[i], new HashSet<>())) return false;
        }
        return true;
    }
    private boolean isSimilar(Map<String, Set<String>> map, String source, String target, Set<String> visited){
        
        Iterator<String> it = map.get(source).iterator();
        while(it.hasNext()){
            String s = it.next();
            if(visited.contains(s)) continue;
            
            if(s.equals(target)) return true;
            visited.add(s);
            if(isSimilar(map, s, target, visited)) return true;
        }
        return false;
    }
}
