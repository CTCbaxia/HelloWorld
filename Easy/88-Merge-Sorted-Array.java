/*
EASY
88. Merge Sorted Array
https://leetcode.com/problems/merge-sorted-array/description/

TIME: 0823 - 30min
RESULT: 27% - 4ms
NOTES: 
1. 从尾部开始 merge，可以避免新建一个 int[]（不会产生覆盖未 merge 的元素）
*/
/*
SOLUTION REFERENCE:
TIME: 0823 - 30min
RESULT: 100% - 3ms
*/
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while(k >= 0 && j >= 0){
            if(i >= 0){
                if(nums1[i] >= nums2[j]) nums1[k--] = nums1[i--];
                else nums1[k--] = nums2[j--];
            }else{
                nums1[k--] = nums2[j--];
            }
        }
        return;
    }
}

/*
SOLUTION 0:
TIME: 0823 - 30min
RESULT: 27% - 4ms
*/

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] merge = new int[m + n];
        int i = 0;
        int j = 0;
        while(i < m  && j < n){
            if(nums2[j] >= nums1[i]) merge[i + j] = nums1[i++];
            else merge[i + j] = nums2[j++]; 
        }
        while(i < m) merge[i + j] = nums1[i++];
        while(j < n) merge[i + j] = nums2[j++];
        
        for(int k = 0; k < merge.length; k++){
            nums1[k] = merge[k];
        }
        return;
    }
}