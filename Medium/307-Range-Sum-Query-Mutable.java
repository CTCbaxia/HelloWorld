/*
MEDIUM
307. Range Sum Query - Mutable

TIME: 
RESULT: 
NOTES:
1. For quick update
2. For quick sumRange: use sum array
3. Segment Tree || Binary Indexed Tree ???

*/
/*
For quick sumRange: use sum array

Time:
update O(n)
sumRange: O(1)
*/
class NumArray {
    int[] sums;//maintain the sums array from 0 to i
    int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
        sums = new int[nums.length];
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            sums[i] = sum;
        }
    }
    
    public void update(int i, int val) {
        
        int diff = val - nums[i];
        nums[i] = val;//modify nums (we will use it in sumRange)
        for(int k = i; k < sums.length; k++){//modify sums
            sums[k] += diff;
        }
        
    }
    
    public int sumRange(int i, int j) {
        return sums[j] - sums[i] + nums[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */


/*
For quick update: compute sum in sumRange

Time:
Update: O(1)
sumRange: O(n)

*/