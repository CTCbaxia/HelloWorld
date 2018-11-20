/*
HARD
689. Maximum Sum of 3 Non-Overlapping Subarrays

TIME: 
RESULT: 
*/
/*
calculate ksum first + Dynamic Programing
然后看如果控制中间模块 i 的移动，左右部分再范围限制内可以取的最大值是多少

Time: O(n)
Space: O(n)
*/
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        
        int[] ksum = new int[len - k + 1];
        for(int i = 0; i < k; i++) ksum[0] += nums[i];//calculate ksum[0]
        for(int i = 1; i < len - k + 1; i++){//calculate ksum[i]
            ksum[i] = ksum[i - 1] + nums[i + k - 1] - nums[i - 1];
        }
        
        //i 之前，使ksum 最大的点出现在哪
        int[] left = new int[ksum.length];//left[i] stores the index of max ksum[j] when j <= i
        int maxindex = 0;
        for(int i = 0; i < left.length; i++){
            if(ksum[i] > ksum[maxindex]) maxindex = i;// bigger ksum found searching from left side
            left[i] = maxindex;//update maxIdx
        }
        
        //i 之后，使ksum 最大的点出现在哪
        int[] right = new int[ksum.length];//right[i] stores the index of max ksum[j] when j >= i
        maxindex = ksum.length - 1;
        for(int i = maxindex; i >= 0; i--){
            if(ksum[i] > ksum[maxindex]) maxindex = i;// bigger ksum found searching from right side
            right[i] = maxindex;//update maxIdx
        }
        
        //find the indices of biggest 3Sum = ksum[l] + ksum[i] + ksum[r]
        int[] res = new int[3];
        int l, r;//left, right index
        int max = Integer.MIN_VALUE;
        for(int i = k; i < ksum.length - k; i++){// for each middle parts, calculate 3Sum[l, i, r]
            l = left[i - k];
            r = right[i + k];
            if(ksum[l] + ksum[i] + ksum[r] > max){// bigger 3Sum found, update
                max = ksum[l] + ksum[i] + ksum[r];
                res = new int[]{l, i, r};
            }
        }
        return res;
        
    }
}