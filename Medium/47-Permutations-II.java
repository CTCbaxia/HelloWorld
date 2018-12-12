/*
MEDIUM
47. Permutations II

TIME: 
RESULT: 
NOTES:

*/
/*
Sort + skip the duplicate (only add duplicate if the previous one is already added)
List<Value>: result
Set<Index>: avoid duplicate

! 这题其实 if(!visited.contains(i - 1)) continue; 换成 if(visited.contains(i - 1)) continue; 也行，主要就是找一个规律
前者是每次先放最前面的重复元素，只有前面的元素放了，才能放后面的元素
后者是每次最先放最后面的一个重复元素（更慢）

Time: O(n!)
Space: O(n)
*/
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums){
        List<List<Integer>>result = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, new ArrayList<Integer>(), new HashSet<Integer>(), result);
        return result;

    }
    private void dfs(int[] nums, List<Integer> res, Set<Integer> visited, List<List<Integer>>result){
        if(res.size() == nums.length){
            result.add(new ArrayList<Integer>(res));
            return;
        } 

        for(int i = 0; i < nums.length; i++){
            if(visited.contains(i)) continue;
            if(i > 0 && nums[i - 1] == nums[i]){
                if(!visited.contains(i - 1)) continue;
            }
            res.add(nums[i]);
            visited.add(i);
            dfs(nums, res, visited, result);
            res.remove(res.size() - 1);
            visited.remove(i);
        }
        return;
    }
}


/*
Swap + DFS
因为所有元素都会用到
permutation {1,2,3,4,1} = 
1, {2,3,4,1}
2, {1,3,4,1}
3, {2,1,4,1}
4, {2,3,1,1}
1, {2,3,4,1} => duplicate, skip(use showed set to avoid duplicate at head)
^
head
然后对{}里面继续dfs permutation

Time: O(n!)
Space: O(1)
*/
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, 0, result);
        return result;

    }
    private void dfs(int[] nums, int start, List<List<Integer>> result){
        if(start == nums.length){
            List<Integer> res = new ArrayList<>();
            for(int n : nums){
                res.add(n);
            }
            result.add(res);
            return;
        } 
        Set<Integer> showed = new HashSet<>();
        for(int i = start; i < nums.length; i++){
            if(showed.contains(nums[i])) continue;//for case[2,2,1,1] 
            
            showed.add(nums[i]);
            swap(nums, start, i);//we also swap with itself
            dfs(nums, start + 1, result);//这里对 start + {} 的 {} 部分做dfs
            swap(nums, start, i);
        }
        return;
    }
    private void swap(int[] nums, int lo, int hi){
        int tmp = nums[lo];
        nums[lo] = nums[hi];
        nums[hi] = tmp;
        return;
    }
}


/*
Backtracking + hashCode

Time: O(n!)
Space: O(n)
*/
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums.length == 0) return result;
        
        List<Integer> res = new ArrayList<Integer>();
        Set<Integer> visited = new HashSet<Integer>();        
        Arrays.sort(nums);
        dfs(nums, result, res, visited);
        return result;
        
    }
    private void dfs(int[] nums, List<List<Integer>> result, List<Integer> res, Set<Integer> visited){
        if(res.size() == nums.length){
            result.add(new ArrayList<Integer>(res));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            //if we already visited this node
            if(visited.contains(i)) continue;
            //if node is the same as previous node and previous has not been visited
            if(i > 0 && nums[i] == nums[i - 1] && visited.contains(i - 1)) continue;
            
            visited.add(i);
            res.add(nums[i]);
            dfs(nums, result, res, visited);
            res.remove(res.size() - 1);
            visited.remove(i);
        }
        return;
    }
}




/*
Swap 不不不对
*/
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> res = new ArrayList<Integer>();
        for(int n : nums){
            res.add(n);
        }
        result.add(res);
        //swap
        for(int i = 0; i < res.size(); i++){
            for(int j = i + 1; j < res.size(); j++){
                if(res.get(i) == res.get(j)) continue;
                swap(res, i, j);
                result.add(new ArrayList<Integer>(res));
                swap(res, j, i);
            }
        }
        return result;
        
    }
    private void swap(List<Integer> res, int l, int r){
        int tmp = res.get(l);
        res.set(l, res.get(r));
        res.set(r, tmp);
        return;
    }
}