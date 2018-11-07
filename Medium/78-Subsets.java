/*
MEDIUM
78. Subsets

TIME: 
RESULT: 
NOTES:
1. Backtracking
2. bit manipulation: https://leetcode.com/problems/subsets/discuss/27278/C++-RecursiveIterativeBit-Manipulation
3. iterative
*/
/*
Combinations: Backtracking

Time: O(2^n) 每个元素都有可能在，也有可能不在
Space: O(n) 最长的 tmp res 有 n 个元素
*/
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        backtracking(nums, 0, result, new ArrayList<Integer>());
        return result;
    }
    private void backtracking(int[] nums, int index, List<List<Integer>> result, List<Integer> res){
        result.add(new ArrayList<Integer>(res));
        for(int i = index; i < nums.length; i++){
            res.add(nums[i]);
            backtracking(nums, i + 1, result, res);
            res.remove(res.size() - 1);
        }
        return;
    }
}