/*
MEDIUM
357. Count Numbers with Unique Digits
https://leetcode.com/problems/count-numbers-with-unique-digits/description/

TIME: 0913 - 2h
RESULT: 100% - 0ms

*/
/*
SOLUTION 0:就是一道排列组合题 combination
TIME: 0913 - 2h
RESULT: 100% - 0ms
*/
class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        int[] digitNum = new int[n + 1];
        
        digitNum[0] = 1;
        if(n == 0) return digitNum[0];
        
        digitNum[1] = 9;
        int sum = digitNum[0] + digitNum[1];
        for(int i = 2; i < n + 1; i++){
            digitNum[i] = digitNum[i - 1] * (11 - i);
            sum += digitNum[i];
        }
        return sum;
    }
}


//ues a set to collect all the inunique number
class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        double total = Math.pow(10, n);
        Set<Integer> repeat = new HashSet<>();
        int res = (int) total;
        for(int i = 0; i < total; i++){
            Set<Integer> digit = new HashSet<>();
            int num = i;
            while(num > 0){
                int tail = num % 10;
                num /= 10;
                if(digit.contains(tail) || repeat.contains(num)){
                    res--;
                    repeat.add(i);
                    break;                    
                } 
                digit.add(tail);
            }
        }
        return res;
    }
}