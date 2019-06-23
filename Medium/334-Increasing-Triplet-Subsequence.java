/*
MEDIUM
334. Increasing Triplet Subsequence

*/
/*
Update values to always give less limitation for the rest of numbers (holds the smallest value for this order number so far)
first: best(smallest) candidate that holds increasing 1 subsequence so far
second: best(smallest) candidate that holds increasing 2 subsequence so far

!!comparison should be num <= hold number

Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean increasingTriplet(int[] nums) {
        if(nums == null || nums.length < 3) return false;
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for(int num : nums){
            //!!shuold be <= to make sure we go to else only when num > curent hold
            if(num <= first) first = num;//if a smallOrEqual number that holds len 1
            else if(num <= second) second = num;//if a smallOrEqual number that holds len 2
            else return true;
        }
        return false;
    }
}




/*
DP: dp[i] = longest len till i
for every number, we know the longest length till this number in this array

Time: O(n^2)
Space: O(n)
*/
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);//for every num, the base len is 1 (itself)
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i] && dp[j] >= dp[i]){
                    dp[i] = dp[j] + 1;
                    if(dp[i] == 3) return true;
                }
            }
        }
        return false;
    }
}





/*
❌ 思路错误：如果有一个很大的数 hold(merge)住了一个很小的数，后面的小一些的数就不能用到那个数了
Maintain a mono decrease stack
when offering a num, remove the previous numbers that is smaller than it, and update the length for that end so far

Time: O(n)
Space: O(n)
*/
class Solution {
    public class MaxLen{
        int max;
        int len;
        public MaxLen(int _max, int _len){
            max = _max;
            len = _len;
        }
    }
    public boolean increasingTriplet(int[] nums) {
        Stack<MaxLen> stack = new Stack<>();
        for(int num : nums){
            MaxLen ml = new MaxLen(num, 1);
            while(!stack.isEmpty() && stack.peek().max < num){
                //merge increasing group with the longest length. ex: 9,1,2,10
                ml.len = Math.max(ml.len, stack.pop().len + 1);
            }
            if(ml.len == 3) return true;
            stack.push(ml);
        }
        return false;
    }
}