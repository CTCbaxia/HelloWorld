/*
Amazon_Longest_Repeated_Substring
https://www.geeksforgeeks.org/longest-repeating-and-non-overlapping-substring/
LCSRe(i, j) stores length of the matching and
            non-overlapping substrings ending 
            with i'th and j'th characters.


*/
class Solution{
    public String longestRepeatedSubstring(String s){
        int len = s.length();
        int maxSize = 0;
        String res = "";
        for(int i = 0; i < len; i++){
            for(int j = i + 1; j < len; j++){
                int k = 0;
                while(k < len - j){
                    if(s.charAt(i + k) == s.charAt(j + k)) k++;
                    else break;
                }
                if(k > maxSize){
                    maxSize = k;
                    res = s.substring(i, i + k);
                }
            }
        }//banana
        return res;
    }
}







class Solution{
    public String longestRepeatedSubstring(String s){
        int n = str.length(); 
        int LCSRe[][] = new int[n + 1][n + 1]; 

        String res = ""; // To store result 
        int res_length = 0; // To store length of result 

        // building table in bottom-up manner 
        int i, index = 0; 
        for (i = 1; i <= n; i++) { 
            for (int j = i + 1; j <= n; j++) { 
                // (j-i) > LCSRe[i-1][j-1] to remove 
                // overlapping 
                if (str.charAt(i - 1) == str.charAt(j - 1) 
                        && LCSRe[i - 1][j - 1] < (j - i)) { 
                    LCSRe[i][j] = LCSRe[i - 1][j - 1] + 1; 

                    // updating maximum length of the 
                    // substring and updating the finishing 
                    // index of the suffix 
                    if (LCSRe[i][j] > res_length) { 
                        res_length = LCSRe[i][j]; 
                        index = Math.max(i, index); 
                    } 
                } else { 
                    LCSRe[i][j] = 0; 
                } 
            } 
        } 

        // If we have non-empty result, then insert all 
        // characters from first character to last 
        // character of String 
        if (res_length > 0) { 
            for (i = index - res_length + 1; i <= index; i++) { 
                res += str.charAt(i - 1); 
            } 
        } 

        return res; 
    }
}
// Java program to find the longest repeated 
// non-overlapping substring 

class GFG { 

// Returns the longest repeating non-overlapping 
// substring in str 
    static String longestRepeatedSubstring(String str) { 
        
    } 

// Driver program to test the above function 
    public static void main(String[] args) { 
        String str = "geeksforgeeks"; 
        System.out.println(longestRepeatedSubstring(str)); 
    } 
} 
// This code is contributed by Rajput-JI 

