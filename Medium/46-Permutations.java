/*
MEDIUM
46. Permutations
TIME: 
RESULT: 30% - 5ms

*/
/*
Backtracking + using boolean array to mark visited

Time: O(n!)
Space: O(n) -- stack and also visited set
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtracking(nums, res, new ArrayList<>(), new boolean[nums.length]);
        return res;
    }
    private void backtracking(int[] nums, List<List<Integer>> res, List<Integer> path, boolean[] visited){
        if(path.size() == nums.length){
            res.add(new ArrayList<Integer>(path));
            return;
        }
        
        for(int i = 0; i < nums.length; i++){
            if(visited[i]) continue;
            visited[i] = true;
            path.add(nums[i]);
            backtracking(nums, res, path, visited);
            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }
}


/*
Backtracking + using hashset to avoid duplication in O(1)

Time: O(n!)
Space: O(n) -- stack and also visited set
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtracking(nums, res, new ArrayList<>(), new HashSet<>());
        return res;
    }
    private void backtracking(int[] nums, List<List<Integer>> res, List<Integer> path, Set<Integer> visited){
        if(path.size() == nums.length){
            res.add(new ArrayList<Integer>(path));
            return;
        }
        
        for(int i = 0; i < nums.length; i++){
            if(visited.contains(nums[i])) continue;
            visited.add(nums[i]);
            path.add(nums[i]);
            backtracking(nums, res, path, visited);
            visited.remove(nums[i]);
            path.remove(path.size() - 1);
        }
    }
}
/*
Backtracking + Swap - 这个方法更优秀

每次 recursion 都对一个 point，以及 point 之后对所有 point 做 swap

why List<Integer> list = Arrays.asList(nums) is not allowed:
https://stackoverflow.com/questions/16748030/difference-between-arrays-aslistarray-and-new-arraylistintegerarrays-aslist

Time: O(n!)
Space: O(1)
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackingSwap(0, nums, res);
        return res;
    }
    private void backtrackingSwap(int start, int[] nums, List<List<Integer>> res){
        if(start == nums.length - 1){
            List<Integer> list = new ArrayList<>();
            for(int n : nums){
                list.add(n);
            }
            res.add(list);
            return;
        }
        
        for(int i = start; i < nums.length; i++){
            swap(nums, start, i);
            backtrackingSwap(start + 1, nums, res);//每次都挑 start 的下一个来一一swap
            swap(nums, i, start);       
        }
    }
    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
















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