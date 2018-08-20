/*
38. Count and Say
https://leetcode.com/problems/find-pivot-index/description/

TIME: 0713 - 30min
RESULT: 7% - 29ms
Notes: 

METHOD:

*/

class Solution {
    public String countAndSay(int n) {

        if(n==1 || n==0){
            return "1";
        }
        String s = countAndSay(n-1);
        String s_n = "";
        int length = s.length();
        int isSame = 1;
        for(int i=0; i<length-1;i++){
            if(s.charAt(i+1)==s.charAt(i)){
                isSame++;
            }else{
                s_n = s_n + isSame + s.charAt(i);
                isSame = 1;
            }
        }
        s_n = s_n + isSame + s.charAt(length-1);
        return s_n;
    }
}