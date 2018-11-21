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

Time: O(n*2^n) 每个元素都有可能在，也有可能不在
Space: O(n) 最长的 tmp res 有 n 个元素
*/
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        backtracking(nums, 0, result, new ArrayList<Integer>());
        return result;
    }
    private void backtracking(int[] nums, int start, List<List<Integer>> result, List<Integer> res){
        result.add(new ArrayList<Integer>(res));//要考虑把每个结果拷贝到result里的时间, 一共2^n个结果, 每个结果平均大小是n, 所以拷贝时间就要n*2^n
        for(int i = start; i < nums.length; i++){
            res.add(nums[i]);
            backtracking(nums, i + 1, result, res);
            res.remove(res.size() - 1);
        }
        return;
    }
}










/*
Combinations: Bit Manipulation
Using [1, 2, 3] as an example, 
1 appears once in every two consecutive subsets, 
2 appears twice in every four consecutive subsets, 
3 appears four times in every eight subsets (initially all subsets are empty):
[], [], [], [], [], [], [], []

[], [1], [], [1], [], [1], [], [1]

[], [1], [2], [1, 2], [], [1], [2], [1, 2]

[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]

需要把第 index 的结果，放入正确的数
Time: O(n * 2^n) len * size = n * 2^n
Space: O(1) 
*/
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int len = nums.length;
        int size = (int) Math.pow(2, len);
        for(int i = 0; i < size; i++){
            result.add(new ArrayList<Integer>());
        } 
        
        for(int i = 0; i < len; i++){//for each nums[i]
            for(int j = 0; j < size; j++){//put them to the right position
                if(((j >> i) & 1) == 1){//if the last i-th bit is 1: j >> i 移到最后一位等于第 i 位
                    result.get(j).add(nums[i]);
                }
            }
        }
        return result;
    }
}