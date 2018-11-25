/*
MEDIUM
75. Sort Colors
https://leetcode.com/problems/sort-colors/description/

TIME: 0903 - 1h
RESULT: 100% - 0ms
NOTES:
1. 好好体会 one pass + swap
*/
/*
bucket sort

Time: O(n)
Space: O(1)
*/
class Solution {
    public void sortColors(int[] nums) {
        int[] bucket = new int[3];
        for(int n : nums){
            bucket[n]++;
        }
        int index = 0;
        for(int i = 0; i < bucket.length; i++){
            while(bucket[i]-- > 0) nums[index++] = i;
        }
        return;
    }
}



/*
Pointers + swap

Time: O(n)
Space: O(1)
*/
class Solution {
    public void sortColors(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        for(int i = 0; i <= hi; i++){//the upper bound should be hi (hi is pointed to unknow value)
            if(nums[i] == 0) swap(nums, i, lo++);//i 扫过的地方不会有 2，nums[lo ~ i] = 1
            else if(nums[i] == 2) swap(nums, i--, hi--);//因为有未知element交换回来了，要调回 i recheck
        }
        return;
    }
    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
        return;
    }
}








/*
SOLUTION REFERENCE: one pass + swap
TIME: 0903 - 15min
RESULT: 100% - 0ms
THOUGHTS:
因为已经知道排序对象的具体值（及其相对大小），可以直接数每个元素的个数 - one pass
然后从新对数组从小到大赋值为相应个数元素 - two pass
*/
class Solution {
    public void sortColors(int[] nums) {
        int red = 0, blue = nums.length - 1, i = 0;
        while(i <= blue){
            if(nums[i] == 0){
                nums[i] = nums[red];
                nums[red++] = 0;
            }
            else if(nums[i] == 2){
                nums[i--] = nums[blue];//如果换过来的为 0，则前进一步就会错过把 0 往前移的机会
                nums[blue--] = 2;
            }
            i++;
        }
        return;
    }
}
//如果没有 nums[i--] 会出错的 test case： [1,2,0]
//不会出现 2,..., 0 然后交换后错过把 2 往后移的机会，因为 i 是从左向后移的，左边的肯定都是 0，不会换过来 2 的。


/*
SOLUTION 0: two pass
TIME: 0903 - 15min
RESULT: 100% - 0ms
THOUGHTS:
因为已经知道排序对象的具体值（及其相对大小），可以直接数每个元素的个数 - one pass
然后从新对数组从小到大赋值为相应个数元素 - two pass
*/
class Solution {
    public void sortColors(int[] nums) {
        int red = 0, white = 0, blue = 0;
        for(int i : nums){
            if(i == 0) red++;
            else if(i == 1) white++;
            else blue++;
        }
        int index = 0;
        while(red > 0 || white > 0 || blue > 0){
            while(red-- > 0) nums[index++] = 0;
            while(white-- > 0) nums[index++] = 1;
            while(blue-- > 0) nums[index++] = 2;
        }
        return;
        
    }
}


/*
SOLUTION 0: two pass
TIME: 0903 - 15min
RESULT: 9% - 1ms
THOUGHTS:
每次都向后统一赋值一遍元素，类似于 insert
*/
class Solution {
    public void sortColors(int[] nums) {
        int red = -1, white = -1, blue = - 1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                nums[++blue] = 2; nums[++white] = 1; nums[++red] = 0;  
            }else if(nums[i] == 1){
                nums[++blue] = 2; nums[++white] = 1; 
            }else if(nums[i] == 2){
                nums[++blue] = 2;
            }
        }
        return;
    }
}