/*
326. Power of Three
https://leetcode.com/problems/power-of-three/description/

TIME: 0715 
RESULT: 92%
NOTES:
1. 1 也是 3 的幂次 （1 是任何数的幂次）
2. 其实 int 范围内，幂次对应的数 < 32 个
METHOD:

*/

/*
METHOD 0: Recursive
RESULT: 60.25% - 19ms 
*/
class Solution {
    public boolean isPowerOfThree(int n) {
        if(n == 0) return false;
        if(n == 1) return true;
        
        if(n % 3 != 0) return false;
        else{
            return isPowerOfThree(n / 3);
        }
    }
}

/*
METHOD 1: Iterative
RESULT: 35% - 21ms
*/
class Solution {
    public boolean isPowerOfThree(int n) {
        if(n == 0) return false;
        if(n == 1) return true;
        
        if(n % 3 != 0) return false;
        else{
            while(n % 3 == 0){
                n = n / 3;
            }
            if(n == 1) return true;
            else return false; 
        }
    }
}
/*
METHOD 2:一个个算 3 的幂次数，直到接近 n
RESULT: 11.6% - 30ms 
*/
class Solution {
    public boolean isPowerOfThree(int n) {
        int remain = n % 10;
        if(remain == 1 || remain == 3 || remain == 9 || remain == 7){
            double i = 0;
            double num = 0;
            if(n > 0){
                while(n > num){
                    num = Math.pow(3,i);
                    i++;
                }
            }else{
                while(n < num){
                    num = Math.pow(-3,i);
                    i++;
                }
            }
            if(n == num) return true; 
            else return false;

        }else{
            return false;
        }
        
    }
}

/*
REFERENCES:
METHOD 3: 算出所有 n 的幂次
RESULT: 
*/
public boolean isPowerOfThree(int n) {
    HashSet<Integer> set = new HashSet<>(Arrays.asList(1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683, 59049, 177147, 531441, 1594323, 4782969, 14348907, 43046721, 129140163, 387420489, 1162261467));
    return set.contains(n);
}

/*
REFERENCES:
METHOD 4: 对于质数来说，n 应该为 maximum 的一个约数
It is worthwhile to mention that Method 1 works only when the base is prime. 
For example, we cannot use this algorithm to check if a number is a power of 4 or 6 or any other composite number.
RESULT: 
*/

public boolean isPowerOfThree(int n) {
    return n > 0 && (1162261467 % n == 0);
}