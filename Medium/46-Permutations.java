/*
MEDIUM
46. Permutations
TIME: 
RESULT: 30% - 5ms

*/
/*
DFS(backtracking) + list 自带去重

Time: O(n!)
Space: O(n)
*/
class Solution {
    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, result, new ArrayList<Integer>());
        return result;
    }   
    private void dfs(int[] nums, List<List<Integer>> result, List<Integer> res){
        if(res.size() == nums.length) result.add(new ArrayList<Integer>(res));

        for(int i = 0; i < nums.length; i++){
            if(res.contains(nums[i])) continue;
            res.add(nums[i]);
            dfs(nums, result, res);
            res.remove(res.size() - 1);//backtracking
        }
        return;
    }
}




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