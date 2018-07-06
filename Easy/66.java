/*
66. Plus One
https://leetcode.com/problems/plus-one/description/

TIME: 0706
RESULT: 100%
NOTE: easy
*/

class Solution {
    public int[] plusOne(int[] digits) {
        int length = digits.length;
        int[] result = plus(digits, length - 1);
        return result;
    }
    private int[] plus(int[] digits, int index){
        if(digits[index] % 10 != 9){
            digits[index] = digits[index] + 1;
            return digits;
        }else{
            digits[index] = 0;
            if(index > 0){
                digits = plus(digits, index - 1); 
            }else{
                int[] newdigits = new int[digits.length + 1];
                newdigits[0] = 1;
                for(int i = 1; i < newdigits.length; i++){
                    newdigits[i] = digits[i - 1];
                }
                return newdigits;
            }
            
        }
        return digits;
    }
}