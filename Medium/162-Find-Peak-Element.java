/*
MEDIUM
162. Find Peak Element
https://leetcode.com/problems/find-peak-element/description/

TIME: 0902 - 20min
RESULT: 99% - 3ms
THOUGHTS: 
1. 二分法 + climbing rising slope 适用于 找一个区段里面的更高值，而不是整个区段里面的最高值
2. 这题的简单设置决定了 Sequential Search 也很适用
*/
/*
Climbing Slope
compare with nums[m + 1]

Time: O(logn)
Space: O(1)
*/
class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l < r){
            int m = l + (r - l)/2;
            if(nums[m] < nums[m + 1]) l = m + 1;//因为 m + 1 永远是 valid 的
            else r = m;//hold the possible peak
        }
        return r;
    }
}




/*
Climbing rising slope

Time: O(logn)
Space: O(1)
*/
class Solution {
    public int findPeakElement(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while(lo < hi){//mid = lo
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] < nums[mid + 1]) lo = mid + 1;//mid + 1 hold the possible peak
            else if(nums[mid] > nums[mid + 1]) hi = mid;//hold the possible peak
        }
        return lo;
    }
}






/*
SOLUTION REFERENCE: 二分法 + climbing rising slope
找一个区段里面的更高值，而不是整个区段里面的最高值

Most people have figured out the binary search solution but are not able to understand how its working. 
What we are essentially doing is going in the direction of the rising slope(by choosing the element which is greater than current). 
How does that guarantee the result? 
Think about it, there are 2 possibilities.
a) rising slope has to keep rising till end of the array 
b) rising slope will encounter a lesser element and go down.

In both scenarios we will have an answer. 
a) the answer is the end element because we take the boundary as -INFINITY 
b) the answer is the largest element before the slope falls. Hope this makes things clearer.
*/
class Solution {
    public int findPeakElement(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while(lo < hi){
            int mid1 = (lo + hi) / 2;
            int mid2 = mid1 + 1;
            if(nums[mid1] > nums[mid2]) hi = mid1;
            else lo = mid2;
        }
        return lo;
    }
}
/*
SOLUTION 0: Sequential Search
如果一个点比他上一个点大，就更新 peak
如果一个点比他上一个点小，就返回上一个点
*/
class Solution {
    public int findPeakElement(int[] nums) {
        if(nums.length == 1) return 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] < nums[i - 1]) return i - 1;
        }
        return nums.length - 1;
    }
}

//复杂一些
class Solution {
    public int findPeakElement(int[] nums) {
        if(nums.length == 0 || nums.length == 1) return 0;
        int peak = nums[0];
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > peak) peak = nums[i];
            else if(nums[i] < peak) return i - 1;
        }
        return nums.length - 1;
    }
}
//最初想法，更复杂一些
//题目说不会有重复元素，所以 nums[i] == peak 不成立
class Solution {
    public int findPeakElement(int[] nums) {
        if(nums.length == 0 || nums.length == 1) return 0;
        int peak = nums[0];
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > peak) peak = nums[i];
            else if(nums[i] < peak) return i - 1;
            else if(i > 0 && nums[i] == peak){
                while(nums[i] == peak && i < nums.length) i++;
                if(i < nums.length) peak = nums[i];
                else return 0;
            }
        }
        if(nums[nums.length - 1] > nums[nums.length - 2]) return nums.length - 1;
        else return 0;
    }
}