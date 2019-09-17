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
Pointers
for every char in s(short),check whether it is in t(using an index)

1. you cannot sort this because you need to maintain the position
2. for each s, you need to check from the 1 to n

Time: O(n)
Space: O(1)

followup:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, 
and you want to check one by one to see if T has its subsequence. 
In this scenario, how would you change your code?

Answer:
The we can use a Map<Character, List<Integer>> to dict every char with its list of indice
We keep a index for T, for every char c in S, we do map.get(c) and find the index larger than current index
    1. here we can use binary search for index in the list
    2. or we can use treemap instead of list, then we also use O(logn) time to find ceiling
update current index

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


