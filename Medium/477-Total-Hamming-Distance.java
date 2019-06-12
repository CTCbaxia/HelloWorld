/*
MEDIUM
477. Total Hamming Distance
这题最难的是把 n^2 变成 n 的思维。用 bit 的概念而非 num 的概念
*/
/*
Math to solve: for every digit, count num of 0 and 1, Combinations for 0 and 1
    0 1 0 0
    0 0 0 1
    0 0 1 1
    0 1 1 1  
0:  4 2 2 1
1:  0 2 2 3
com = 4*0 + 2*2 + 2*2 + 1*3

Time: O(n * 32)
Space: O(1)
*/
class Solution {
    public int totalHammingDistance(int[] nums) {
        int count = 0;
        int totalNum = nums.length;
        for(int i = 0; i < 32; i++){
            int numOfOne = 0;
            for(int n : nums){//loop all num in nums
              numOfOne += (n >> i) & 1;//count the total num of 1 for that digit
            }
            count += (totalNum - numOfOne) * numOfOne;//num of 0 * num of 1
        }
        return count;
    }
}



/*
Use XOR to see diff between two nums

Time: O(n^2 * 32)
Space: O(1)
*/
class Solution {
    public int totalHammingDistance(int[] nums) {
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                res += countOne(nums[i] ^ nums[j]);//1 for diff
            }
        }
        return res;
    }
    private int countOne(int diff){
        int count = 0;
        for(int i = 0; i < 32; i++){
            if(((diff >> i) & 1 ) == 1){
                count++;
            }
        }
        return count;
    }
}



/*
Time: O(n^2 * 32)
*/
class Solution {
    public int totalHammingDistance(int[] nums) {
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                res += hammingDistance(nums[i], nums[j]);
            }
        }
        return res;
    }
    private int hammingDistance(int n1, int n2){
        int count = 0;
        for(int i = 0; i < 32; i++){
            int d1 = (n1 >> i) & 1;
            int d2 = (n2 >> i) & 1;
            if(d1 != d2) count++;
        }
        return count;
    }
}