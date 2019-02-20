/*
384. Shuffle an Array
*/
/*
类似于 Reservoir Sampling -- 逐个随机
[x,x,x,x]
0: 对于每个数来说，被放到 0th 的概率为 1/n
1: 对于每个数来说，被放到 1th 的概率为 (n - 1)/n * 1/(n - 1) = 1/n (需要第一轮没有抽中)
....
所以对于每个数来说，被放到某个位置的概率均为 1/n


Time: O(n)
Space: O(1)
*/
class Solution {
    Random rand;
    int[] original;
    public Solution(int[] nums) {
        rand = new Random();
        original = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return original;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] res = original.clone();
        for(int i = 0; i < original.length; i++){
            int range = original.length - i;
            int index = i + rand.nextInt(range);
            swap(res, i, index);
        }
        return res;
    }
    public void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */






/*
Random on index and remove

Time: O(n^2)
Space: O(n)
*/
class Solution {
    Random rand;
    List<Integer> list;
    int[] nums;
    public Solution(int[] nums) {
        rand = new Random();
        list = new ArrayList<>();
        this.nums = nums;
        for(int n : nums){
            list.add(n);
        }
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] res = new int[nums.length];
        List<Integer> tmp = new ArrayList<>(list);
        for(int i = 0; i < nums.length; i++){
            int index = rand.nextInt(tmp.size());
            res[i] = tmp.get(index);
            tmp.remove(index);// need O(n) to remove
        }
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */