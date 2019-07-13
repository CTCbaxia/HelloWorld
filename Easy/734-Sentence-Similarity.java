/*
M
734. Sentence Similarity
做法一样。
*/
/*
Map + Set + one pointer to check

n = len of words, m = len of pairs
Time: O(m + n)
Space: O(m)
*/
class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
        if(words1.length != words2.length) return false;
        //map the pairs
        Map<String, Set<String>> map = new HashMap<>();
        for(int i = 0; i < pairs.size(); i++){
            String s1 = pairs.get(i).get(0);
            String s2 = pairs.get(i).get(1);
            map.putIfAbsent(s1, new HashSet<>());
            map.putIfAbsent(s2, new HashSet<>());
            map.get(s1).add(s2);
            map.get(s2).add(s1);
        }    
        
        //check the similarity
        int n = words1.length;
        for(int i = 0; i < n; i++){
            if(words1[i].equals(words2[i])) continue;//if the word are the same
            if(!map.containsKey(words1[i])) return false;
            if(!map.get(words1[i]).contains(words2[i])) return false;
        }
        return true;
    }
}

/*
Map + Set + one pointer to check

n = len of words, m = len of pairs
Time: O(m + n)
Space: O(m)
*/
class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
        if(words1.length != words2.length) return false;
        //map the pairs
        Map<String, Set<String>> map = new HashMap<>();
        for(int i = 0; i < pairs.size(); i++){
            String s1 = pairs.get(i).get(0);
            String s2 = pairs.get(i).get(1);
            if(!map.containsKey(s1)) map.put(s1, new HashSet<>());
            if(!map.containsKey(s2)) map.put(s2, new HashSet<>());
            map.get(s1).add(s2);
            map.get(s2).add(s1);
        }    
        
        //check the similarity
        int n = words1.length;
        for(int i = 0; i < n; i++){
            if(words1[i].equals(words2[i])) continue;//if the word are the same
            if(!map.containsKey(words1[i])) return false;
            if(!map.get(words1[i]).contains(words2[i])) return false;
        }
        return true;
    }
}

/*
Map + Set + one pointer to check

n = len of words, m = len of pairs
Time: O(m + n)
Space: O(m)
*/
class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
        if(words1.length != words2.length) return false;
        //map the pairs
        Map<String, Set<String>> map = new HashMap<>();
        for(int i = 0; i < pairs.size(); i++){
            String s1 = pairs.get(i).get(0);
            String s2 = pairs.get(i).get(1);
            
            Set<String> l1 = map.getOrDefault(s1, new HashSet<>());
            Set<String> l2 = map.getOrDefault(s2, new HashSet<>());
            l1.add(s2);
            l2.add(s1);
            map.put(s1, l1);
            map.put(s2, l2);
        }    
        
        //check the similarity
        int n = words1.length;
        for(int i = 0; i < n; i++){
            if(words1[i].equals(words2[i])) continue;//if the word are the same
            if(!map.containsKey(words1[i])) return false;
            if(!map.get(words1[i]).contains(words2[i])) return false;
        }
        return true;
    }
}

