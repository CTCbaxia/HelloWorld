/*
MEDIUM
524. Longest Word in Dictionary through Deleting
https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/

TIME: 0905 - 30min
RESULT: 44% - 43ms //同样的解法有更高的
NOTES:
1. int compareTo(String str) : If first string is lexicographically greater than second string, it returns positive number (difference of character value). 
	if s1 > s2, it returns positive number  
	if s1 < s2, it returns negative number  
	if s1 == s2, it returns 0 
	也就是返回第一个不同 character 的值 
*/
/*
SOLUTION 0: two pointers
TIME: 0905 - 30min
RESULT: 44% - 43ms
THOUGHTS:
对于字典里面的每一个元素都 check 是不是 s 的子集，是的话把小一些的存到结果

*/

class Solution {
    public String findLongestWord(String s, List<String> d) {
        String res = "";
        for(int i = 0; i < d.size(); i++){
            String target = d.get(i);
            int index = 0;
            for(int j = 0; j < target.length(); j++){
                while(index < s.length() && target.charAt(j) != s.charAt(index)){
                    index++;
                }
                if(index < s.length()) index++;
                else break;
                
                if(j == target.length() - 1){
                    if(res.length() < target.length()) res = target;
                    else if(res.length() == target.length() && ) res = target;
                }
            }
            
        }
        return res;
    }
}
