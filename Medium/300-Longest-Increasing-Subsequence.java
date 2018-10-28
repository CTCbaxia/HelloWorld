/*
MEDIUM
300. Longest Increasing Subsequence

TIME: 
RESULT: 99% - 1ms
NOTES:
binary search 的总结
*/
/*
Binary Search

Time: O(nlogn)
Space: O(n)
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] len = new int[nums.length];
        int size = 0;
        for(int i = 0; i < nums.length; i++){
            int lo = 0;
            int hi = size;
            //其实这里的 mid 是永远不可能等于 hi 的
            while(lo < hi){
                int mid = lo + (hi - lo)/2;
                if(len[mid] < nums[i]) lo = mid + 1;
                else if(len[mid] >= nums[i]) hi = mid;
            }
            //这里找到的 hi 是顺序里第一个大于或等于 nums[i] 的数，或者就是现有数的 size
            len[hi] = nums[i];
            if(hi == size) size++;
            
        }
        return size;
    }
}


/*
Binary Search
方法不是很优化

Time: O(nlogn)
Space: O(n)
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] len = new int[nums.length + 1];
        int size = 0;
        for(int i = 0; i < nums.length; i++){
            int lo = 0;
            int hi = size;
            while(lo < hi){
                int mid = lo + (hi - lo)/2;
                if(len[mid] < nums[i]) lo = mid + 1;
                else if(len[mid] > nums[i]) hi = mid;
                else break;
            }
            
            if(len[hi] <= nums[i]){
                if(hi == size) size++;
                len[hi + 1] = nums[i];
            }else if(len[hi] > nums[i]){
                len[hi] = nums[i];
            }
        }
        return size;
    }
}



/*
DP

Time: O(n^2)
Space: O(n)
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] len = new int[nums.length];
        len[0] = 1;
        int result = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]) len[i] = Math.max(len[i], len[j] + 1);
                else len[i] = Math.max(len[i], 1);
            }
            result = Math.max(result, len[i]);
        }
        return result;
    }
}





//Binary Search

/*
1. 一般需要找到某个特定的值才会两边都增减
2. 如果有多个 target
	lo = mid 帮助 hold 最后一个出现的值，hi = mid - 1
	hi = mid 帮助 hold 第一个出现的值，lo = mid + 1
*/
while(lo < hi){
	int mid = lo + (hi - lo)/2;
	if(nums[mid] < x) lo = mid + 1;
	else if(nums[mid] > x) hi = mid - 1;
}
//lo == hi
nums[lo] 有可能 > x
nums[hi] 有可能 < x


/*
找第一个大于 x 的值
*/
while(lo < hi){
	int mid = lo + (hi - lo)/2;
	if(nums[mid] < x) lo = mid + 1;
	else if(nums[mid] > x) hi = mid;//永远会在大于 x 的地方等着，并一直逼近最接近 x 的大值
}
//lo == hi
nums[lo] = nums[hi] > x



while(lo < hi){
	int mid = lo + (hi - lo)/2;
	if(nums[mid] < x) lo = mid;
	else if(nums[mid] > x) hi = mid - 1;
}
//有可能死循环？
nums[lo] ? x



