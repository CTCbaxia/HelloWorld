/*
494. Target Sum
https://leetcode.com/problems/target-sum/description/

RESULT: TIME LIMITED
*/
class Solution {
    public int findTargetSumWays(int[] nums, int S) {

        int res = 0;
        List<Integer> result = new ArrayList<Integer>();
        result.add(0);
        for(int i = 0; i < nums.length; i++){
            result = sub(nums[i], result);
            
        }
        
        for(int j = 0; j < result.size(); j++){
            if( result.get(j) == S){
                res = res + 1;
            }
        }
        return res;
        
    }
    private List<Integer> sub(int number, List<Integer> sum){
        List<Integer> next = new ArrayList<Integer>();
        for(int i = 0; i < sum.size(); i++){
            next.add(sum.get(i) + number);
        }
        for(int i = 0; i < sum.size(); i++){
            next.add(sum.get(i) - number);
        }
        return next;
    }
}