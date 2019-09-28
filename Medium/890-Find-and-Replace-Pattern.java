/*
MEDIUM
890. Find and Replace Pattern

*/
/*
String Mask
Normalize the words to number of showsup
abb = "1-2-2"
abc = "1-2-3"
abcb = "1-2-3-2"
normalize every word and compare the array

Time: O(mn)
Space: O(1)
*/
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        String p = normalize(pattern);
        List<String> res = new ArrayList<>();
        for(String w : words){
            if(normalize(w).equals(p)) res.add(w);
        }
        return res;
    }
    private String normalize(String w){
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < w.length(); i++){
            char c = w.charAt(i);
            if(!map.containsKey(c)) map.put(c, map.size());
            sb.append(map.get(c)).append("-");
        }
        return sb.toString();
    }
}





/*
Two Map:
注意：双向映射才是一一对应的。
否则 abc -> amm，对于单一 “左 -> 右” 映射也成立

Time: O(n)
Space: O(26)
*/
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for(String word : words){
            Map<Character, Character> m1 = new HashMap<>();//映射 word to parttern
            Map<Character, Character> m2 = new HashMap<>();
            int i = 0;
            for(; i < word.length(); i++){
                char w = word.charAt(i);
                char p = pattern.charAt(i);
                if(!m1.containsKey(w)) m1.put(w, p);
                if(!m2.containsKey(p)) m2.put(p, w);
                if(m1.get(w) != p || m2.get(p) != w) break;//如果至少有一个方向的映射有误

            }
            if(i == word.length()) result.add(word);
        }
        return result;
    }
}

/*
One Map + One Set
Time: O(n)
Space: O(26)
*/
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for(String word : words){
            Map<Character, Character> map = new HashMap<>();//映射 word to parttern
            Set<Character> set = new HashSet<>();//收集 pattern 里面已经被映射过的，不能有重复映射
            int i = 0;
            for(; i < word.length(); i++){
                char w = word.charAt(i);
                char p = pattern.charAt(i);
                if(map.containsKey(w)){
                    if(map.get(w) != p) break;
                }else{
                    if(set.contains(p)) break;
                    map.put(w, p);
                    set.add(p);
                }
            }
            if(i == word.length()) result.add(word);
        }
        return result;
    }
}

/*
Normalize:
normalize the string using the key and the number of key so far
对于 word and pattern，转化成一个数组，对于每一位，对应的值为：这是第几个新出现的key

Time: O(n * len) n is the number of word in words, len is the len of pattern
Space: O(26)
*/
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        int[] p = normal(pattern);
        
        for(String word : words){
            int[] w = normal(word);
            if(Arrays.equals(p, w)) result.add(word);
        }
        return result;
    }
    private int[] normal(String s){
        int n = s.length();
        int[] res = new int[n];
        Map<Character, Integer> map = new HashMap<>();//key, order of key first seen
        
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            map.putIfAbsent(c, map.size());
            res[i] = map.get(c);
        }
        return res;
    }
}

