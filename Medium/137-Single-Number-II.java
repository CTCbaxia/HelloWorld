/*
MEDIUM
137. Single Number II

TIME: 
RESULT: 
NOTES:
http://liadbiz.github.io/leetcode-single-number-problems-summary/
https://leetcode.com/problems/single-number-ii/discuss/43295/Detailed-explanation-and-generalization-of-the-bitwise-operation-method-for-single-numbers
*/
/*
Bit Calculation
for 32 bit, check every bit if that bit is 1 in the single number

Time: O(n)
Space: O(1)
*/
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int i = 0; i < 32; i++){
            int count = 0;//count 1 for that bit
            for(int n : nums){
                if(((n >> i) & 1) == 1) count++;
            }
            res += (count % 3) << i;//count % 3 = 1 or 0
        }
        return res;
    }
}






/*
Bit Calculation
for 32 bit, check every bit if that bit is 1 in the single number

Time: O(n)
Space: O(1)
*/
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int i = 0; i < 32; i++){
            int sum = 0;//sum how many 1 in that bit
            for(int k = 0; k < nums.length; k++){
                if((nums[k] >> i & 1) == 1){
                    sum++;
                }
            }
            sum = sum % 3;
            if(sum == 1) result += 1 << i;
        }
        return result;
    }
}





/*
Bit Manipulation:

set ^ num -- add num to set

"(ones ^ A[i]) & ~twos" basically means perform the above mentioned operation if and only if A[i] is not present in the set "twos". So to write it in layman:

   IF the set "ones" does not have A[i]
       Add A[i] to the set "ones" if and only if its not there in set "twos"
   ELSE
       Remove it from the set "ones"

one: number shows only exactly once
two: number shows only exactly two



Time: O(n)
Space: O(1)
*/
class Solution {
    public int singleNumber(int[] nums) {
        int one = 0, two = 0;
        for(int i = 0; i < nums.length; i++){
            one = (one ^ nums[i]) & ~two;//add nums[i] to one group only if it is not in two
            two = (two ^ nums[i]) & ~one;//add nums[i] to two group only if it is not in one
        }
        return one;
    }
}