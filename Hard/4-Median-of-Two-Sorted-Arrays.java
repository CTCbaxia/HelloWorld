/*
HARD
4. Median of Two Sorted Arrays

TIME: 
RESULT: 
*/
/*
对于一个数组做二分法，在另外一个数组中找到对应分割点
check这两个分割点是否是的左右set的元素，左 < 右

p1 + p2 = (len1 + len2 + 1)/2
p1 代表所有在 nums1[p1 - 1] 之前的元素都在left set，一共 p1 个
p2 代表所有在 nums2[p2 - 1] 之前的元素都在left set，一共 p2 个

(len1 + len2 + 1)/2 的大范围保证了如果一共有奇数个元素，left 会有多一个元素
even: (Math.max(maxLeft1,maxLeft2) + Math.min(minRight1,minRight2))/2
odd: (Math.max(maxLeft1,maxLeft2))

Time: O(log(min(len1, len2)))
Space: O(1)

https://www.youtube.com/watch?time_continue=1244&v=LPFhl65R7ww
*/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2){
        /*
        make sure len of nums1 is smaller than nums2
        因为如果两个不一样长，永远会有剩余的元素在长的数组里面，作为left set
        而短的数组可能会全部被选中到 right set
        当len nums1 < len nums2 时
        计算 index2 = (len1 + len2 + 1)/2 - index1 可能会出现负数
        */
        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2, nums1);
        } 

        int len1 = nums1.length;
        int len2 = nums2.length;

        int lo = 0, hi = len1;//因为left = [nums[0] ... nums[p1 - 1]], 所以要保证有可能整个 nums1 都被选中，包括len1 - 1
        while(lo <= hi){
            int p1 = lo + (hi - lo)/2;
            int p2 = (len1 + len2 + 1)/2 - p1;//make sure the left set is equal(even case) or 1 more than right(odd)

            int maxLeft1 = p1 == 0 ? Integer.MIN_VALUE : nums1[p1 - 1];
            int minRight1 = p1 == len1 ? Integer.MAX_VALUE : nums1[p1];
            
            int maxLeft2 = p2 == 0 ? Integer.MIN_VALUE : nums2[p2 - 1];
            int minRight2 = p2 == len2 ? Integer.MAX_VALUE : nums2[p2];

            if(maxLeft1 <= minRight2 && maxLeft2 <= minRight1){//cut point found
                if((len1 + len2) % 2 == 0){//even
                    return (double)(Math.max(maxLeft1,maxLeft2) + Math.min(minRight1,minRight2))/2;
                }else{//odd
                    return (double)(Math.max(maxLeft1,maxLeft2));
                }
            }else if(maxLeft2 > minRight1){
                lo = p1 + 1;
            }else{
                hi = p1 - 1;
            }
        }
        return -1;

    }
}