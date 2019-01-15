/*
EASY
482. License Key Formatting

*/

/*
Go from the end of the string and reverse
Be careful to deal with the last "-"

Time: O(n)
Space: O(n)
*/
class Solution {
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int len = 0;
        for(int i = S.length() - 1; i >= 0; i--){
            char c = S.charAt(i);
            if(Character.isLetterOrDigit(c)){
                sb.append(Character.toUpperCase(c));
                len++;
                if(len == K){
                    sb.append('-');
                    len = 0;
                }
            }
        }
        if(sb.length() == 0) return sb.toString();//for "---"
        else if(sb.charAt(sb.length() - 1) == '-')
            sb.deleteCharAt(sb.length() - 1);
        
        return sb.reverse().toString();
    }
}