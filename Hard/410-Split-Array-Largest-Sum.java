/*
H
410. Split Array Largest Sum
*/
/*
Greedy + Try each possible value in Binary Search
min possible capacity = Max individual value
max possible capacity = sum of all

Time: O(nlogn) logn to find the answer, spend n for each possible answer
Space: O(1)
*/
class Solution {
    public int splitArray(int[] nums, int m) {
        long left = 0;
        long right = 0;
        for(int num : nums){
            left = Math.max(left, num);
            right += num;
        }
        while(left < right){
            
            long mid = left + (right - left)/2;
            //try using mid to divide, if cur sum larger than mid, move to next spot
            if(validCapacity(nums, mid, m)){
                right = mid;
            }else{
                left = mid + 1;//cannot meet, should be bigger
            }
        }
        //right == left
        return (int) right;
    }
    private boolean validCapacity(int[] nums, long c, int m){
        //if there can be divided by this capacity, return the largest sum in the capacity
        //if not, return -1
        int curSum = 0;
        for(int i = 0; i < nums.length; i++){
            if(curSum + nums[i] <= c){
                curSum += nums[i];
            }else{
                m--;
                if(m == 0) return false;
                curSum = nums[i];
            }
        }
        return true;
    }
}
