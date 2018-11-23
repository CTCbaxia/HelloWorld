/*
MEDIUM
491. Increasing Subsequences

TIME: 
RESULT: 
NOTES:
permutation 的一种
*/
/*
Backtracking + Set
to deal with duplicate: in every layer, only add 1st duplicate element, and skip for the rest

We want to see if we start from a specific nums[i] till end, what path will yield a valid result
For every layer we loop all elements that haven't been used (start from index)
And for the same layer we don't want duplicate num, so we use set to help
We output result along the way once the result is larger than size 2

Time: O(2^n)  # result if input nums in ascending order, for every element, choose or not
Space: O(1)
*/
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }
    private void dfs(int[] nums, int start, List<Integer> res, List<List<Integer>> result){
        if(res.size() >= 2) result.add(new ArrayList<Integer>(res));
        Set<Integer> visited = new HashSet<>();
        for(int i = start; i < nums.length; i++){
            if(visited.contains(nums[i])) continue;
            if(res.size() == 0 || res.get(res.size() - 1) <= nums[i]){
                res.add(nums[i]);
                visited.add(nums[i]);
                dfs(nums, i + 1, res, result);
                res.remove(res.size() - 1);                
            }
        }
        return;
    }
}



/*
Backtracking:
We want to see if we start from a specific nums[i] till end, what path will yield a valid result

For every layer we loop all elements that haven't been used (start from index)
And for the same layer we don't want duplicate num, so we use set to help
We output result along the way once the result is larger than size 2


Time: O(2^n)  # result if input nums in ascending order, for every element, choose or not
Space: O(1)
*/
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        findHelper(result, new ArrayList<Integer>(), nums, 0);
        return result;
    }
    private void findHelper(List<List<Integer>> result, List<Integer> l, int[] nums, int index){
        if(l.size() > 1) result.add(new ArrayList<>(l));
        
        Set<Integer> visited = new HashSet<Integer>();
        for(int i = index; i < nums.length; i++){//start from index
            if(visited.contains(nums[i])) continue;//if duplicate, continue
            
            if(l.size() == 0 || l.get(l.size() - 1) <= nums[i]){
                l.add(nums[i]);
                findHelper(result, l, nums, i + 1);
                l.remove(l.size() - 1);
                visited.add(nums[i]);
            }

        }
        return;
    }
}





/*
Since the subarray should has the same order as in the original input array
Build the result while loop the nums array
if previous end <= the nums[i], we add nums[i] to the end and update these new list to the result
Also, we add the nums[i] itself to the result to combine with later elements

1,2,3,3

the list
--for 1
1
--for 2
1, 2
2
--for 3
1, 3
1, 2, 3
2, 3
3
--for 3
1, 3 *
1, 2, 3 *
2, 3 *
3 *

1, 3, 3
1, 2, 3, 3
2, 3, 3
3, 3

And we have duplicate * and also single number
We make sure inserting the single number to the front so remove easily then
And we use set to remove the duplicate and reload them back to the result
*/

class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> level = null;
        for(int i = 0; i < nums.length; i++){
            int size = result.size();
            for(int j = 0; j < size; j++){
                List<Integer> l = result.get(j);
                
                if(l.get(l.size() - 1) <= nums[i]){
                    //if two equals, we only add after the equal value 
                    level = new ArrayList<>(l);
                    level.add(nums[i]);
                    result.add(level);
                }       

            }
            level = new ArrayList<>();//add the single element nums[i] for further sequences
            level.add(nums[i]);
            result.add(0, level);
        }
        for(int i = 0; i < nums.length; i++) result.remove(0);
        Set<List<Integer>> res = new HashSet<>(result);
        result = new ArrayList<>(res);
        return result;
    }
}