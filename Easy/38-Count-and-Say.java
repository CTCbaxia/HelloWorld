/*
38. Count and Say
https://leetcode.com/problems/find-pivot-index/description/

TIME: 0713 - 30min
RESULT: 7% - 29ms

*/
/*
Iteration: while 同类元素 get count

Time: O(n)
Space: O(n)
*/
class Solution {
    public String countAndSay(int n) {
        if(n == 0) return "";
        String cur = "1";
        while(--n > 0){
            String pre = cur;
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < pre.length(); i++){
                int count = 1;
                while(i + 1 < pre.length() && pre.charAt(i) == pre.charAt(i + 1)){
                    count++;
                    i++;
                }
                sb.append(count).append(pre.charAt(i));
            }
            cur = sb.toString();
        }
        return cur;
    }   
}






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