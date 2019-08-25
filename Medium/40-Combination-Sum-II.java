/*
M
40. Combination Sum II
*/
/*
DFS/back tracking

Time: O(2^n)
Space: O(1) - O(n) for stack
*/
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);//remove duplicate
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, 0, target, new ArrayList<Integer>(), res);
        return res;
    }
    //add or not add
    private void dfs(int[] candidates, int index, int target, List<Integer> cur, List<List<Integer>> res){
        if(target == 0) res.add(new ArrayList<>(cur));// need to check it first
        else if(index >= candidates.length || target < 0) return;
        
        for(int i = index; i < candidates.length; i++){
            if(i > index && candidates[i] == candidates[i - 1]) continue;//skip duplicate
            cur.add(candidates[i]);
            dfs(candidates, i + 1, target - candidates[i], cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}
