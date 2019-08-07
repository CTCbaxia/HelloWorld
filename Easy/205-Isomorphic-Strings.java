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
/*
Two pointers + map the char to a same value

The reason we want to use map is to create a relation between key and value.
mapping a->b is a general way,
but mapping a->1, b->1 is also a effective way

Time: O(n)
Space: O(256)
*/
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        int[] cs = new int[256];
        int[] ct = new int[256];
        for(int i = 0; i < n; i++){
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            if(cs[charS] != ct[charT]) return false;
            cs[charS] = i + 1;//avoid mapping to 0 when i is 0, mix with something ummaped
            ct[charT] = i + 1;
        }
        return true;
    }
}
