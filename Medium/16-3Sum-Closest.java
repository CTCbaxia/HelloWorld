/*
16. 3Sum Closest
https://leetcode.com/problems/3sum-closest/description/

TIME: 0804
RESULT: 78% - 11ms
思路：
先排序，按照顺序得到尽可能有序的加和，如果上一个还在 target 的一边，下一个就到了 target 的另一边，那么 result 一定在而者之一取最近的
方法 1 
和 threeSum 一样，两头的指针

方法 2 
两个定位针，一个指针二分法
*/


/*
Solution 0: 83.07%


*/
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[nums.length - 1];
        for (int i = 0; i < nums.length - 2; i++){
            if(i == 0 || (i > 0 && nums[i] != nums[i-1])){
                int lo = i + 1;
                int hi = nums.length - 1; 
                while(lo < hi){
                    int sum = nums[i] + nums[lo] + nums[hi];
                    if(sum == target){
                        return target;
                    }else if (sum < target){
                        while(lo < hi && nums[lo] == nums[lo + 1]) lo ++;
                        lo++;
                        
                    }else{
                        while(lo < hi && nums[hi] == nums[hi - 1]) hi --;
                        hi--;
                    }       
                    if(Math.abs(sum - target) < Math.abs(result - target)){
                        result = sum;
                    }
                    
                    
                }

            }
            
        }
        return result;
    }
}


/*
总结：
这种题说你可以假设某种情况（只有一个答案），就说明你不需要考虑有其他情况你的结果和 expected answer 是不是一致
这种题目就是设置一个大循环遍历，再设置两个小指针两边夹近
*/