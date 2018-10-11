/*
5. Longest Palindromic Substring

Solution: 95%

Note：
这里注意，当回文为奇数和偶数两种情况，可能匹配的结果是不一样的。比如 cbbbb 这种如果按奇数配，就只有 bbb，如果按偶数配，就有bbbb

遍历中间节点

*/
O(n^2)
class Solution {
    int maxLen = 0;
    int lo = 0;
    public String longestPalindrome(String s) {
        //if(s.length() < 2) return s;
        for(int i = 0; i < s.length(); i++){
            findPalindrome(s, i, i);
            if(i + 1 < s.length()) findPalindrome(s, i, i + 1);
        }
        return s.substring(lo, lo + maxLen);
    }
    private void findPalindrome(String s, int j, int k){
        while(j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)){
            j--;
            k++;
        }
        if(maxLen < k - j - 1){
            maxLen = k - j - 1;
            lo = j + 1;
        }
    }
}




class Solution {
    public String longestPalindrome(String s) {


        if (s.length() == 0){
            return s;
        }
        
        String substring = "" + s.charAt(0);
        int longest = 0;
        for(int i = 0; i < s.length() - 1; i++){
            int start_L = 0;
            int start_R = 0;
            if(s.charAt(i) == s.charAt(i + 1)){
                start_L = i;
                start_R = i+1;  
                longest = start_R - start_L + 1;
                int j = 1;
                while(start_L-j >=0 && start_R+j <s.length()){
                    if(s.charAt(start_L - j) == s.charAt(start_R + j)){
                        j = j + 1;
                        longest = longest + 2;
                    }else{
                        break;
                    }

                }
                if(substring.length() < longest){
                    substring = s.substring(start_L - j + 1, start_R + j);
                }else{
                    longest = 0;
                }
                
                
            }
            
            if (i > 0 && s.charAt(i - 1) == s.charAt(i + 1)) {                   
                start_L = i - 1;    
                start_R = i + 1; 
                longest = start_R - start_L + 1;
                int j = 1;
                while(start_L-j >=0 && start_R+j <s.length()){
                    if(s.charAt(start_L - j) == s.charAt(start_R + j)){
                        j = j + 1;
                        longest = longest + 2;
                    }else{
                        break;
                    }

                }
                if(substring.length() < longest){
                    substring = s.substring(start_L - j + 1, start_R + j); //substring 这里 endindex 是可以超过边界的
                }else{
                    longest = 0;
                }
                
            }   
        
        }
        return substring;
    }
}