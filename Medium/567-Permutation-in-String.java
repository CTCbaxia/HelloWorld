/*
MEDIUM
567. Permutation in String

TIME: 
RESULT: 
NOTES:

*/
/*
Sliding Window (two pointers)
window 里面只装没有问题的 substring（如果有匹配多了的，也把多的那部分去掉

Time: O(n)
Space: O(26)
*/
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] words = new int[26];
        for(char c : s1.toCharArray()){
            words[c - 'a']++;//count each letter
        }
        int count = s1.length();//num need to be matched
        int lo = 0, hi = 0;
        char[] ch2 = s2.toCharArray();
        while(hi < ch2.length){//sliding window
            if(words[ch2[hi] - 'a']-- > 0){
                count--;
                if(count == 0) return true;
            }else{
                while(words[ch2[hi] - 'a'] < 0){//once we meet a wrong letter, lo++ till no wrong letter
                    words[ch2[lo] - 'a']++;
                    if(words[ch2[lo] - 'a'] > 0) count++;
                    lo++;
                }
            }
            hi++;
        }
        return false;
    }
}





/*
Sliding Window (two pointers)

Time: O(n)
Space: O(26)
*/
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() == 0) return true;
        
        int[] count = new int[26];
        for(char c : s1.toCharArray()){//count each letter
            count[c - 'a']++;
        }
        
        int lo = 0, hi = 0;
        char[] ch2 = s2.toCharArray();
        while(lo <= hi && hi < ch2.length){//sliding window
            count[ch2[hi] - 'a']--;
            if(count[ch2[hi] - 'a'] < 0){
                
                while(count[ch2[hi] - 'a'] < 0){//once we meet a wrong letter, we move lo till no wrong letter
                    count[ch2[lo] - 'a']++;
                    lo++;
                }
            }
            if(hi - lo + 1 == s1.length()) return true;//since substring (lo - hi) is alwasy valid, once their size == s1, done
            hi++;
        }
        return false;
    }
}