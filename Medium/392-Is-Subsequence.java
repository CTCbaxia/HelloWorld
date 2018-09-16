/*
MEDIUM
392. Is Subsequence
https://leetcode.com/problems/is-subsequence/description/

follow up link
792. Number of Matching Subsequences
https://leetcode.com/problems/number-of-matching-subsequences/description/

TIME: 0915 - 20min
RESULT: 94% - 2ms
NOTES: 
1.!!!!! index++是执行完操作之后++
2. String.indexOf(char c, int fromIndex) find c in string from index 
3. 新建 List<Integer>[] charS = new list[26];
*/
/*
SOLUTION 0: 
TIME: 0915 - 15min
RESULT: 57% - 22ms
1. you cannot sort this because you need to maintain the position
2. for each s, you need to check from the 1 to n
*/
class Solution {
    public boolean isSubsequence(String s, String t) {
        int len = t.length();
        int index = 0;
        for(char c : s.toCharArray()){
            while(index < len && t.charAt(index) != c) index++;
            if(index >= len) return false;//if didn't match, return
            index++;//注意这里要动起来，没有这句就会反复停留在已经 match 的元素。eeee就会认为存在了
        }
        return true;
    }
}
/*
SOLUTION REFERENCE: 
TIME: 0915 - 20min
RESULT: 94% - 2ms
*/
class Solution {
    public boolean isSubsequence(String s, String t) {
        int len = t.length();
        for(int i = 0, index = 0; i < s.length(); i++){
            index = t.indexOf(s.charAt(i), index);
            if(index++ <= 0) return false;
        }
        return true;
    }
}


//follow up solution 


