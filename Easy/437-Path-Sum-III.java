/*
MEDIUM
437. Path Sum III

TIME: 
RESULT: 
NOTES:

*/
/*
DFS (backtracking) go through every path + Prefix Sum Map
一样是思路：subarray sum = nums[j] - nums[i]
Map: <presum, number of way to get that presum>

only keep cumulative sum in one path
so need to remove once finish traverse that path (backtracking)


Time: O(n)
Space: O(logn)
*/
class Solution {
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> preSum = new HashMap<Integer, Integer>();
        preSum.put(0, 1);///!!!fake sum, 如果选择 path 目前为止所有的值，有一种选法
        return pathHelper(root, 0, preSum, sum);
    }
    private int pathHelper(TreeNode node, int curSum, Map<Integer, Integer> preSum, int target){
        if(node == null) return 0;
        
        curSum += node.val;//current sum for this sigle path
        int res = preSum.getOrDefault(curSum - target, 0);//curSum - preSum = target (中间部分就是答案)
        preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);//这条 path 里多少能达到 curSum 的值
        res += pathHelper(node.left, curSum, preSum, target);
        res += pathHelper(node.right, curSum, preSum, target);
        
        preSum.put(curSum, preSum.get(curSum) - 1);//backtrack 的时候移走这条track的sum，以免影响后面的另一条track
        return res;
    }
}

//if we want sum from the root to the node, we don't need the map, just keep the curSum in backtracking
class Solution {
    public int pathSum(TreeNode root, int sum) {
        return pathHelper(root, 0, sum);
    }
    private int pathHelper(TreeNode node, int curSum, int target){
        if(node == null) return 0;
        curSum += node.val;//current sum for this sigle path
        if(curSum == target) res++;
        res += pathHelper(node.left, curSum, target);
        res += pathHelper(node.right, curSum, target);
        
        curSum -= node.val;//backtrack 的时候移走这条track的sum，以免影响后面的另一条track
        return res;
    }
}












/*
Update path value

Time: O()
Space: O(n)
*/
class Solution {
    public int pathSum(TreeNode root, int sum) {
        return pathHelper(root, new ArrayList<Integer>(), sum);
    }
    private int pathHelper(TreeNode node, List<Integer> list, int sum){
        if(node == null) return 0;
        int res = 0;
        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();
        res += pathHelper(node.left, left, sum);
        res += pathHelper(node.right, right, sum);

        list.addAll(left);
        list.addAll(right);
        for(int i = 0; i < list.size(); i++){
            int add = list.get(i) + node.val;
            if(add == sum) res++;
            list.set(i, add);
        }
        list.add(node.val);
        if(node.val == sum) res++;
        return res;
    }

}