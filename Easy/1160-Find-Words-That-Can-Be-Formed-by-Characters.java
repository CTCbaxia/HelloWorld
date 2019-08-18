/*
E
1160. Find Words That Can Be Formed by Characters
*/
/*
Char Count

Time: O(n * avg)
Space: O(26 * n)
*/
class Solution {
    public int countCharacters(String[] words, String chars) {
        char[] count = new char[26];
        for(char c : chars.toCharArray()){
            count[c - 'a']++;
        }
        int res = 0;
        for(String w : words){
            char[] curCount = count.clone();
            int i = 0;
            for(; i < w.length(); i++){
                if(curCount[w.charAt(i) - 'a'] == 0) break;
                else{
                    curCount[w.charAt(i) - 'a']--;
                }
            }
            if(i == w.length()) res += w.length();
        }
        return res;
    }
}
