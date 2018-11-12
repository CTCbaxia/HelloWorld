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
    int[] sum;//maintain the sum array from 0 to i
    int[] num;
    public NumArray(int[] nums) {
        num = nums;
        sum = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            sum[i] += nums[i];
            if(i > 0) sum[i] += sum[i - 1];
        }
    }
    
    public void update(int i, int val) {
        //modify sum
        int diff = val - num[i];//-1
        for(int k = i; k < sum.length; k++){
            sum[k] += diff;//update the sum after i with diff
        }
        
        //modify num (we will use it in sumRange)
        num[i] = val;
    }
    
    public int sumRange(int i, int j) {
        return sum[j] - sum[i] + num[i];
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