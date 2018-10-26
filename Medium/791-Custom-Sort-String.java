/*
MEDIUM
791. Custom Sort String

TIME: 
RESULT: 100% - 2ms
NOTES:
这题是典型的 counting sort 变种
对于重构一个 string，没有要求 inplace 的话，可以想想 stringbuilder 重新构造一个 string
*/
/*
counting chars in T
put keys in count into sb
put rest in count into sb

Time: O(n)
Space: O(26)
*/
class Solution {
    public String customSortString(String S, String T) {
        //counting each char in T
        int[] count = new int[26];
        for(char c : T.toCharArray()) count[c - 'a']++;
        
        //put keys in S first into sb
        StringBuilder sb = new StringBuilder();
        for(char c : S.toCharArray()){
            while(count[c - 'a']-- > 0) sb.append(c);
        }
        
        //put rest of T into sb
        for(char c = 'a'; c <= 'z'; c++){
            while(count[c - 'a']-- > 0) sb.append(c);
        }
        return sb.toString();
    }
}

/*
**in-place:
first partition the T with [keys | no keys]
build counting map
customer sort T using order in S

Time: O(s+t)
Space: O(s) <= O(26)
*/
class Solution {
    public String customSortString(String S, String T) {
        //initialize the map of S
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(char c : S.toCharArray()){
            map.put(c, 0);
        }
        //partition the T
        char[] chT = T.toCharArray();
        int index = 0;
        for(int i = 0; i < chT.length; i++){
            if(map.containsKey(chT[i])){//swap
                char tmp = chT[index];
                chT[index] = chT[i];
                chT[i] = tmp;
                index++;
            }
        }
        //counting key, index is the number of keys in T
        for(int i = 0; i < index; i++){
            map.put(chT[i], map.get(chT[i]) + 1);
        }
        
        //put keys at right place
        index = 0;
        for(char c : S.toCharArray()){
            int num = map.get(c);
            while(num-- > 0) chT[index++] = c;
        }
        return new String(chT);
    }
}