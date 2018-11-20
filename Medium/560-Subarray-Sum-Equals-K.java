/*
MEDIUM
560. Subarray Sum Equals K
https://leetcode.com/problems/subarray-sum-equals-k/description/

TIME: 0713 - 4h
RESULT: 2.17% - 574ms
NOTES:
1. 注意传参部分：pre++ 和 pre + 1 的区别
2. 双重循环 SUM[i, j] = SUM[0, j] - SUM[0, i - 1] 的思想很重要
3. HashMap 该学了
*/
/*
PreSum + Map
连续的 subarry sum -- presum
加速寻找 -- hashmap(remember map.put(0, 1))

Time: O(n)
Space: O(n)
*/
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();//sum, num of that sum
        map.put(0, 1);//if all elments, there are one solution
        int sum = 0;
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(map.containsKey(sum - k)){
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
        
    }
}









/*
METHOD 0: 参考 discuss
Solution 1. 
Brute force. We just need two loops (i, j) and test if SUM[i, j] = k. 
Time complexity O(n^2), Space complexity O(1). I bet this solution will TLE.

Solution 2. 
From solution 1, we know the key to solve this problem is SUM[i, j]. 
So if we know SUM[0, i - 1] and SUM[0, j], then we can easily get SUM[i, j]. 
To achieve this, we just need to go through the array, calculate the current sum and save number of all seen PreSum to a HashMap. 
Time complexity O(n), Space complexity O(n).


RESULT: 96% - 23ms
*/
class Solution {
public int subarraySum(int[] nums, int k) {
    int result = 0; 
    int sum = 0;
    
    Map<Integer, Integer> preSum = new HashMap<Integer, Integer>();//HashMap(sum, number_of_that_sum)
    preSum.put(0,1);
    
    for(int i = 0; i < nums.length; i++){
        sum = sum + nums[i];
        if(preSum.containsKey(sum - k)){
            result = result + preSum.get(sum - k);
        }
        preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
    }
    
    return result;

    }
}



/*
METHOD 1: 
基础解题思路：固定左边的值，向后累加，如果 match 就结果 + 1

RESULT: 24% - 211ms
Time: O(n^2)
*/
class Solution {
public int subarraySum(int[] nums, int k) {
    int count=0;
    for(int i = 0; i < nums.length; i++){
        int sum = nums[i];
        if(sum == k) count++;
        for(int j = i + 1; j < nums.length; j++){
            sum = sum + nums[j];
            if(sum == k) count++;
        }

    }
    return count;
    }
}
//同上 presum思想

class Solution {
    public int subarraySum(int[] nums, int k) {
        if(nums.length == 0) return 0;
        int result = 0;
        int[] sum = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(i == 0) sum[i] = nums[i];
            else{
                sum[i] += sum[i - 1] + nums[i];
            }
        }      
        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                if(sum[j] - sum[i] + nums[i] ==k) result++;
            }
        }
        return result;
        
    }
}

/*
METHOD: 同上
利用 dp 的思路，但是因为不知道要多大的一个二维数组，所以不知道怎么用数组表示出预设结果...
如果要取某个 value， 那么 subarray 数量 result(n, k) 等于 result(n + 1, k - nums[n]) 的迭代之和

RESULT: 2.17% - 574ms
*/
class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int[] dp = new int[nums.length];
        for(int i = nums.length - 1; i >=0; i--){
            dp[i] = result(i, k, nums, 0);
        }
        for(int i = nums.length - 1; i >=0; i--){
            sum = sum + dp[i];
        }
        return sum;
    }
    private int result(int n, int k, int[] nums, int pre){
        if(n == nums.length - 1){
            if(nums[n] == k) return (pre + 1);
            else return pre;
        }
        int res = 0;
        if(nums[n] == k){
            res = result(n + 1, k - nums[n], nums, pre + 1);
        }else{
            res = result(n + 1, k - nums[n], nums, pre);
        }
        return res;
    }
   
}


/*
METHOD:
两个指针的思想
设定一个长度可变的窗口 windows，窗口的两边分别是指针 left 和 right
若窗口内部的 value < k， 将 left 向右移动扩大窗口
若窗口内部的 value > k， 将 right 向右移动缩小窗口
若 left == right（且 value != k），将 right 向右

RESULT: Wrong Code
窗口的思路太不固定，两边指针很随机
很多 case 没有考虑到：有 0 和有 -n 的
比如：0,1,1,1,1,-1,1,-2,2,2
*/
class Solution {
    public int subarraySum(int[] nums, int k) {

        int left = 0; 
        int right = 0;
        int count = 0;
        int sum = 0;
        while(left < nums.length){
            
            if(right > nums.length - 1){
                left++;
                if(left < nums.length){
                    sum = sum - nums[left];
                    if(sum == k){
                        count++;
                    }                    
                }

            }else{
                if(left <= right){
                    sum = 0;
                    for(int i = left; i <= right; i++){
                        sum = sum + nums[i];
                    }

                    if(sum == k){
                        count++;
                        //left++;
                        right++;
                    }else if(sum < k){
                        if(nums[left] < 0) left++;
                        else right++;
                    }else{
                        if(right < nums.length - 1 && nums[right + 1] <= 0) right++;
                        else left++;
                    } 
                }else{
                    right++;
                } 
            }


            System.out.println("left:" + left + "right:" + right);
        }
        return count;
    }
}
