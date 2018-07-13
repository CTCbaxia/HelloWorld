/*
MEDIUM
15. 3 Sum
https://leetcode.com/problems/3sum/description/

TIME: 0713
RESULT: 92.73 %

Notes:
这个思路的优秀之处在于，直接挪动指针来规避重复项，而不是等到匹配且形成 LIST 之后再来和之前的项比较。
因为既然你在 Solution 1 里面也只是和相邻匹配项比较，为什么不更早的就在匹配之前规避呢。
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        for(int i = 0; i < nums.length - 2; i++){
            if(i == 0 || (i > 0 && nums[i] != nums[i - 1])){
                int lo = i + 1;
                int hi = nums.length - 1;
                while(lo < hi){
                    if(nums[i] + nums[lo] + nums[hi] == 0){
                        result.add(Arrays.asList(nums[i],nums[lo],nums[hi]));
                        while(lo < hi && nums[lo] == nums[lo + 1] ) {lo++;}//这里应该限制它不能无限网上增，否则若后面所有数都相等，则会超量
                        while(lo < hi && nums[hi] == nums[hi - 1] ) {hi--;}//另外注意顺序 nums[lo] == nums[lo + 1] && lo < hi，这样会先判断前面的，而这个时候就超空间了
                        lo++;
                        hi--;
                    }else if (nums[i] + nums[lo] + nums[hi] < 0){
                        lo++;
                    }else{
                        hi--;
                    }
                }
            }


        }
        
        
        return result;
    }
}


