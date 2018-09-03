/*
MEDIUM
80. Remove Duplicates from Sorted Array II
https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/

TIME: 0903 - 10min
RESULT: 100% - 0ms
NOTES:
Two Pointer 的思想不只在两个指针同时遍历，也在一个指针遍历，一个指针等待结果
*/


/*
SOLUTION REFERENCE: two pointer
TIME: 0903 - 10min
RESULT: 100% - 0ms
THOUGHTS:
一个指针用来查找 valid 的值，另一个指针用来赋值
*/
//更简洁
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        int index = 0;
        for(int n : nums){
            if(index < 2 || n > nums[index - 2])
                nums[index++] = n;
        }
        return index;
    }

}
//更 intuitive 一些
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        int count = 0;
        int num = nums[0];
        int index = 0;
        for(int n : nums){
            if(n == num) count++;
            else{
                count = 1;
                num = n;
            }
            if(count <= 2) nums[index++] = n;
        }
        return index;
    }

}




/*
SOLUTION 0: 顺序遍历 + 左移
TIME: 0903 - 30min
RESULT: 7.13% - 2ms
THOUGHTS:
当找到多余的重复值后，将第一个不重复的后续序列（截止到 end ）左移到第一个重复的位置
*/
//稍微改进了一点，一次性找到所有多余重复值之后的值
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        
        int num = nums[0];
        int count = 1;
        int end = nums.length - 1;
        for(int i = 1; i <= end; i++){
            if(nums[i] == num){
                count++;
            }else{
                num = nums[i];
                count = 1;
            }
            if(count > 2){
                int start = i;
                while(start <= end && nums[start] == num){
                    start++;
                }//start 最终为第一个不等于 num 的点
                if(start > end) return i;
                moveLeft(nums, i, start, end);
                end -= start - i;
                i--;
            } 
        }
        return end + 1;
    }
    private void moveLeft(int[] nums, int index, int start, int end){
        for(int i = start; i <= end; i++){
            nums[index++] = nums[i];
        }
        return;
    }
}
//return 的 int 为多少，就输出 nums 的前多少个数字
//也就是要改变 nums 的前 n 个数字，然后返回 n
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        
        int num = nums[0];
        int count = 1;
        int end = nums.length - 1;
        for(int i = 1; i <= end; i++){
            if(nums[i] == num){
                count++;
                if(count > 2) moveLeft(nums, i--, end--);
            }else{
                num = nums[i];
                count = 1;
            }
        }
        return end + 1;
    }
    private void moveLeft(int[] nums, int start, int end){
        int last = nums[start];
        for(int i = start; i < end; i++){
            nums[i] = nums[i + 1];
        }
        nums[end] = last;
    }
}