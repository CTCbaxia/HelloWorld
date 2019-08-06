/*
E
205. Isomorphic Strings
*/
/*
Two pointers + 2 map

Time: O(n)
Space: O(256)
*/
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        for(int i = 0; i < n; i++){
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            if(map1.containsKey(charS) && map1.get(charS) != charT || map2.containsKey(charT) && map2.get(charT) != charS) return false;
            map1.put(charS, charT);
            map2.put(charT, charS);
        }
        return true;
    }
}