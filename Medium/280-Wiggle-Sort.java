/*
MEDIUM
280. Wiggle Sort
https://leetcode.com/problems/wiggle-sort/description/

TIME: 1006 - 30min
RESULT: 100% - 0ms
当目前没有满足的时候，说明有递增或递减序列。只需要 swap，就可以实现。
因为三个数中，前两个一定是满足规律的，那么第三个只需要和第二个对调，就一定也满足第一个和第二个的规律。（第三个只会比第二个更大或者更小）
*/
class Solution {
    public void wiggleSort(int[] nums) {
        if(nums.length == 0) return;
        int sign = -1;
        int index1 = 0; 
        int index2 = 1;
        while(index2 < nums.length){
            if((nums[index1] - nums[index2]) * sign < 0){
                int tmp = nums[index1];
                nums[index1] = nums[index2];
                nums[index2] = tmp;
            }
            sign = -sign;
            index1++;
            index2++;
        }
        return;
    }
}