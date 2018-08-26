/*
MEDIUM
39. Combination Sum
https://leetcode.com/problems/combination-sum/description/

TIME: 
RESULT: 0825 - 3h
NOTES: 46% - 13ms
0. https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
1. 用 list 等变量的时候，记得在写结果的时候直接新建一个，以防后续的增减操作改变你之前的结果： result.add(new ArrayList<Integer>(list));
2. backtracking 记得要变回原样：list.remove(list.size() - 1); 并且是最后一位，而不是某个 object

*/
/*
SOLUTION REFERENCE:
RESULT: 0825 - 20min
NOTES: 46% - 13ms

:*/
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        backtracking(candidates, 0, target, result, new ArrayList<Integer>());
        return result;
        
    }
    private void backtracking(int[] candidates, int index, int remain, List<List<Integer>> result, List<Integer> list){
        if(remain < 0) return;
        else if(remain == 0){
            result.add(new ArrayList<Integer>(list));//这里很重要，你是新建了一个 list，这样就不会被后续的增减操作影响了
            return;
        }else{
            for(int i = index; i < candidates.length; i++){
                list.add(candidates[i]);
                backtracking(candidates, i, remain - candidates[i], result, list);
                list.remove(list.size() - 1);//注意不是 list.remove(candidates[i]); 因为这里是 int 型，只会移除第 candidates[i] 位的元素，如果没有那么多，会 error
            }
        }
        return;
    }
}
/*
思路对的，但是没写出来
1. 先排序
2. 从选第一位开始，再遍历一遍，直到得到结果，或者那一轮最小的和都超过。
3. 对于每一轮子遍历，如果有一个值等于 target，那么剩下的遍历不可能等于，直接continue；
4. 对于每一轮子遍历，如果第一个值大于 target，那么剩下的遍历均大于，直接continue；
*/
//还没有去重
//还没有剪枝
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for(int i = 0; i < len; i++){
            if(candidates[i] ==  target){
                List<Integer> comb = new ArrayList<Integer>();
                comb.add(candidates[i]);
                res.add(comb);
                break;
            }else if(candidates[i] > target){
                break;
            }else{
                for(int j = i; j < len; j++){
                    System.out.println(candidates[i] + "  "+ candidates[j]);
                    if(candidates[i] + candidates[j] == target){
                        List<Integer> comb = new ArrayList<Integer>();
                        comb.add(candidates[i]);
                        comb.add(candidates[j]);
                        res.add(comb);
                        break;                        
                    }else if(candidates[i] + candidates[j] > target){
                        break;
                    }else{
                        List<Integer> comb = new ArrayList<Integer>();
                        comb.add(candidates[i]);
                        comb.add(candidates[j]);
                        dfs(candidates, i, target - candidates[i] - candidates[j], comb, res); 
                    }
                    System.out.println("res: " + res);
                }
            }
        }
        return res;
    }
    private void dfs(int[] candidates, int index, int remain, List<Integer> comb, List<List<Integer>> res){
        System.out.println(res);
        for(int i = index; i < candidates.length; i++){
             System.out.println(i + ":" + candidates[i]);
            if(remain - candidates[i] == 0){
                comb.add(candidates[i]);
                Collections.sort(comb);
                
                for(List l : res){
                    if(l.hashCode() == comb.hashCode()) return;
                }
                res.add(comb);
                return;
            }else if(remain - candidates[i] > 0){
                comb.add(candidates[i]);
                int count = res.size();
                int count2 = comb.size();
                dfs(candidates, index, remain - candidates[i], comb, res);
                if(res.size() == count + 1 || comb.size() == count){
                    
                    break;
                    
                }else{
                    comb.remove(candidates[i]);
                    
                }
            }else{
                return;
            }
        }
        return;
    }
}