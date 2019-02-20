/*
EASY
169. Majority Element

*/
/*
Boyer-Moore Voting Algorithm --- 有点 tricky

Time: O(n)
Space: O(1)
*/

class Solution {
    public int majorityElement(int[] nums) {
        if(nums.length == 0) return 0;
        int major = nums[0];
        int count = 1;
        for(int i = 1; i < nums.length; i++){
            if(count == 0){//如果 major 积攒的都被抵消了，那么它不可能是答案
                major = nums[i];
                count++;
            }else if(major == nums[i]){//如果 major 还没有抵消完，且增加了一个
                count++;
            }else{//抵消一个
                count--;
            }
        }
        return major;
    }
}





/*
Map
Time: O(n)
Space: O(n)
*/

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int n = nums.length/2;
        for(int i : map.keySet()){
            if(map.get(i) > n) return i;
        }
        return 0;
    }
}

/*
Sort
Time: O(nlogn)
Space: O(1)
*/
// tricky
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];//窗口大于 1/2 size 的数字一定会出现在中间点
    }
}


// normal
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length/2;
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            if(i == 0 || nums[i] == nums[i - 1]){
                count++;
            }else{
                if(count > n) return nums[i - 1];
                count = 1;
            }
        }
        if(count > n) return nums[nums.length - 1];//不要忘记出口处的比较
        return 0;
    }
}

