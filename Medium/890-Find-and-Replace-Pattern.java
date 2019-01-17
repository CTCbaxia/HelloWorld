/*
MEDIUM
890. Find and Replace Pattern

*/
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
                if(m1.get(w) != p || m2.get(p) != w) break;

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