/*
136. Single Number
https://leetcode.com/problems/single-number/description/

TIME: 0722 - 10min
RESULT: 100% - 0ms
NOTES:

METHOD:
1. HashSet
2. bitwise XOR :https://leetcode.com/problems/single-number/discuss/43201/Easy-Java-solution-(tell-you-why-using-bitwise-XOR)
    0 ^ N = N
    N ^ N = 0
    XOR operator is commutative

    
*/
/*
XOR
Time: O(n)
Space: O(1)
*/
class Solution {
public int singleNumber(int[] nums){
        int res = 0;
        for(int n : nums){
            res ^= n;
        }
        return res;
    }  
}


/*
Hashset

Time: O(n)
Space: O(n)
*/
class Solution {
    public int singleNumber(int[] nums){
        Set<Integer> set = new HashSet<>();
        for(int n : nums){
            if(set.contains(n)) set.remove(n);
            else set.add(n);
        }
        return set.iterator().next();
    }
}





//--------2 ROUND FOR MS ------------------------------------------------
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int n : nums){
            result ^= n;
        }
        return result;
    }
}




//--------1 ROUND ------------------------------------------------

/*
SOLUTION REFERENCE:

TIME: 0722 - 10min
RESULT: 100% - 0ms
*/
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            res = res ^ nums[i];
        }
        return res;
    }
}

/*
SOLUTION 1:

TIME: 0722 - 10min
RESULT: 29% - 9ms
*/

class Solution {
    public int singleNumber(int[] nums) {
        Set<Integer> doublenumber = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(!doublenumber.contains(nums[i])) doublenumber.add(nums[i]);
            else doublenumber.remove(nums[i]);
        }
        Iterator<Integer> it = doublenumber.iterator();
        return it.next();
    }
}


