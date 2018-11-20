/*
MEDIUM
209. Minimum Size Subarray Sum
https://leetcode.com/problems/minimum-size-subarray-sum/description/

TIME: 0721 - 1h
RESULT: 26% - 3ms
NOTES:
1. 值不确定不能用 map: SUM[i,j] = SUM[0,j] - SUM[0, i - 1] >= s 
*/

/*
Presum + Two Pointer(l, r)
find all good subarray and update result
l <= r, 不能让 l 走到大于 r，最多 l == r 能让结果成立，那就是存在解的情况下最小的 size = 1

Time: O(n)
Space: O(n)
*/
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int[] sum = new int[nums.length];
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            count += nums[i];
            sum[i] = count;
        }
        int res = Integer.MAX_VALUE;
        int l = 0, r = 0;
        while(l <= r && r < sum.length){//if l == r, sum[r] - sum[l] + nums[l] = nums[l] >= s, res = 1 
            if(sum[r] - sum[l] + nums[l] < s){
                r++;
            }else{
                res = Math.min(res, r - l + 1);
                l++;
            }
        }
        
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}





/*
presum + binary search to find smallOrEqual(largest index)

lo = -1, hi = i - 1

Time: O(nlogn)
Space: O(n)
*/
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int[] sum = new int[nums.length];
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            count += nums[i];
            sum[i] = count;
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < sum.length; i++){
            int target = sum[i] - s;//need to find the largest index that sum[index] <= target
            if(target < 0) continue;//all elements from 0 to i will not satisfy
            
            //if target >= 0, at least all elements from 0 to i could
            int lo = -1, hi = i - 1;//finally lo = -1 if all > target, but since target >= 0, i is good
            while(lo < hi){
                int mid = lo + (hi - lo)/2 + 1;//biased
                if(sum[mid] > target) hi = mid - 1;
                else lo = mid;
            }
            res = Math.min(res, i - hi);
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
















class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length == 0) return 0;
        int result = nums.length + 1;
        int left = 0; 
        int right = 0;
        int sum = nums[right];
        while(right < nums.length && left <= right){
                if(sum >= s){
                    if(left == right) return 1;
                    else{
                        result = Math.min(result, right - left + 1);
                        sum = sum - nums[left];
                        left++;   
                    }
                }else if(sum < s){
                    right++;
                    if(right >= nums.length) break;
                    sum = sum + nums[right];
                }
        }
        if(result == nums.length + 1) return 0;
        return result;
    }
}