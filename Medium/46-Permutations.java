/*
MEDIUM
46. Permutations


TIME: 
RESULT: 30% - 5ms

*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        dfs(nums, new ArrayList<Integer>(), result);
        return result;
    }
    private void dfs(int[] nums, List<Integer> res, List<List<Integer>> result){
        if(res.size() == nums.length){
            result.add(new ArrayList<Integer>(res));
            return;
        }
        for(int n : nums){
            if(!res.contains(n)){
                res.add(n);
                dfs(nums, res, result);
                res.remove(res.size() - 1);
            }
        }
        return;
    }
}