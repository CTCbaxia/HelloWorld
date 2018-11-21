/*
MEDIUM
31. Next Permutation
https://leetcode.com/problems/next-permutation/description/

TIME: 0711 - 40min
RESULT: 99% - 11ms
NOTES:
1. 跟排序有关的题用 Arrays.sort() 可能不太好，需要自己写排序。但是这题其实只涉及翻转；
2. 做题之前先一步步分析这题有哪些情况，分析清楚了比直接考虑所有情况要快很多；

思路：
1. 从最后 2 位看起
    if(非 desc) 换位
    if(desc) 向前看一位
        从最后 3 位看起
            if(非desc e.i. 最高位小于上一个最高位) 从剩下两位里面找出略大于最高位的值，替换掉最高位，剩下的数按照 asc 排列（其实就是翻转，因为只有 desc 才会走到这一步）
            if(desc) 向前看一位
                ...
                if(最高位) return asc

*/
/*
Find drop and swap, and reverse the remain

Time: O(n)
Space: O(1)
*/
class Solution {
    public void nextPermutation(int[] nums) {
        for(int i = nums.length - 2; i >= 0; i--){
            if(nums[i] >= nums[i + 1]) continue;
            
            //eles: i is the drop
            int j = nums.length - 1;
            while(j > i && nums[j] <= nums[i]) j--;//从后往前，找到第一个大于 nums[i] 的index
            
            swap(nums, i , j);//把大值挪到前面，剩下的reverse
            reverse(nums, i + 1, nums.length - 1);
            return;
        }
        reverse(nums, 0, nums.length - 1);
        return;
    }
    private void swap(int[] nums, int m, int n){
        int tmp = nums[m];
        nums[m] = nums[n];
        nums[n] = tmp;
        return;
    }
    private void reverse(int[] nums, int lo, int hi){
        while(lo < hi){
            swap(nums, lo++, hi--);
        }    
        return;
    }
    
}



/*
Find drop and swap, and sort

find swap 对象是用 binary search，但是没太大必要


Time: O(n)
Space: O(1)
*/        
class Solution {
    public void nextPermutation(int[] nums) {
        for(int i = nums.length - 2; i >= 0; i--){
            if(nums[i] >= nums[i + 1]) continue;
            
            //else: i is the drop
            int lo = i + 1, hi = nums.length - 1;//find the largest index that nums[index] > nums[i]
            while(lo < hi){
                int mid = lo + (hi - lo) / 2 + 1;
                if(nums[mid] <= nums[i]) hi = mid - 1;
                else lo = mid;
            }
            swap(nums, i , lo);//把大值挪到前面，剩下的reverse
            reverse(nums, i + 1, nums.length - 1);
            return;
        }
        reverse(nums, 0, nums.length - 1);
        return;
    }
    private void swap(int[] nums, int m, int n){
        int tmp = nums[m];
        nums[m] = nums[n];
        nums[n] = tmp;
        return;
    }
    private void reverse(int[] nums, int lo, int hi){
        while(lo < hi){
            swap(nums, lo++, hi--);
        }    
        return;
    }
    
}

























class Solution {
    public void nextPermutation(int[] nums) {
        if(nums.length <= 1) return;
        for(int i = nums.length - 2; i >= 0; i--){
            if(nums[i] >= nums[i + 1]){
                if(i == 0){
                    Arrays.sort(nums);
                    return;
                }else{
                   continue; 
                }
                
            }else{
                for(int j = nums.length - 1; j >= i + 1; j--){
                    if(nums[j] > nums[i]){
                        int tmp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = tmp;
                        break;
                    }
                }
                Arrays.sort(nums, i + 1, nums.length);
                return;
            }
        }
        return;
    }
}

class Solution {
    public void nextPermutation(int[] nums) {
        if(nums.length <= 1) return;
        for(int i = nums.length - 2; i >= 0; i--){
            if(nums[i] >= nums[i + 1]){
                if(i == 0){
                    //Arrays.sort(nums);
                    reverseNums(nums, 0, nums.length);
                    return;
                }else{
                   continue; 
                }
                
            }else{
                for(int j = nums.length - 1; j >= i + 1; j--){
                    if(nums[j] > nums[i]){
                        int tmp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = tmp;
                        break;
                    }
                }
                //Arrays.sort(nums, i + 1, nums.length);//可能不能这么投机取巧噢
                reverseNums(nums, i + 1, nums.length);
                return;
            }
        }
        return;
    }
    private void reverseNums(int[] nums, int start, int end){
        for(int i = 0; i < (end - start)/2; i++){
            int tmp = nums[start + i];
            nums[start + i] = nums[end - 1 - i];
            nums[end - 1 - i] = tmp;
        }
    }
}