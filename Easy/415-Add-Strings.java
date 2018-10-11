/*
EASY
415. Add Strings
https://leetcode.com/problems/add-strings/description/

TIME: 0823 - 30min
RESULT: 28% - 21ms
NOTES: 

RELATED:
1. 43. Multiply Strings
*/

class Solution {
    public String addStrings(String num1, String num2) {
        char[] n1 = num1.toCharArray();
        char[] n2 = num2.toCharArray();
        int i = n1.length - 1; 
        int j = n2.length - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while(i >= 0 || j >= 0){
            int digit1 = (i < 0) ? 0 : n1[i] - '0';
            int digit2 = (j < 0) ? 0 : n2[j] - '0';
            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            sb.append(sum = sum % 10);
            i--;
            j--;
        }
        if(carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }
}

/*
SOLUTION 0:
TIME: 0823 - 30min
RESULT: 28% - 21ms
*/
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;
        int addup = 0;
        
        while(l1 >= 0 || l2 >= 0){
            int digit1 = (l1 < 0) ? 0 : num1.charAt(l1--) - '0';
            int digit2 = (l2 < 0) ? 0 : num2.charAt(l2--) - '0';
            int sum = (digit1 + digit2 + addup) % 10;
            addup = (digit1 + digit2 + addup) / 10;
            sb.append(sum);
        }

        if(addup > 0) sb.append(addup);
        sb.reverse();
        return sb.toString();
            
    }
}
