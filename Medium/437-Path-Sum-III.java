/*
MEDIUM
437. Path Sum III

TIME: 
RESULT: 
NOTES:

*/
/*
Prefix Sum
一样是思路：subarray sum = nums[j] - nums[i]

Time: O(n)
Space: O(logn)
*/
class Solution {
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> preSum = new HashMap<Integer, Integer>();
        preSum.put(0, 1);// sum 为0有默认的一种解法，什么都不选
        return pathHelper(root, 0, preSum, sum);
    }
    private int pathHelper(TreeNode node, int curSum, Map<Integer, Integer> preSum, int target){
        if(node == null) return 0;
        
        curSum += node.val;//current sum for this sigle path
        int res = preSum.getOrDefault(curSum - target, 0);//curSum - preSum = target (中间部分就是答案)
        preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);//这条 path 里多少能达到 curSum 的值
        res += pathHelper(node.left, curSum, preSum, target) + pathHelper(node.right, curSum, preSum, target);
        
        preSum.put(curSum, preSum.getOrDefault(curSum, 0) - 1);//backtrack 的时候移走这条track的sum，以免影响后面的另一条track
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