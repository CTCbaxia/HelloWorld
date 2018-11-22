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
Two Pointers

Time: O(n)
Space: O(1)

be careful: if nums2 have more to move into nums1 but p1 < 0
[0]
0
[1]
1
*/
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1, p = m + n - 1;
        while(p1 >= 0 && p2 >= 0 && p > p1){//when p = p1, all nums2 move into nums1
            if(nums1[p1] >= nums2[p2]){//all nums after p1 will be 覆盖
                nums1[p] = nums1[p1];
                p1--;
            }else{
                nums1[p] = nums2[p2];
                p2--;
            } 
            p--;
        }
        while(p2 >= 0) nums1[p--] = nums2[p2--];
        return;
    }
}






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