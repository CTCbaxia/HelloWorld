/*
1. Two Sum
https://leetcode.com/problems/two-sum/description/

TIME: 0713 - 30min
RESULT: 99.4%, 4ms

NOTES: 
用 HashMap 来存储数据。
Hashmap 根据键的 hashCode 值存储数据，大多数情况下可以直接定位到它的值，因而具有很快的访问速度，为了将时间复杂度降到 O(n)，可以用 Hashmap
*/

//-------2 ROUND FOR MS-----------------------------------------
//one pass
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])) return new int[]{i, map.get(target - nums[i])};
            map.put(nums[i], i);
        }
        return new int[2];
    }
}


//two pass
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], i);//注意这里后面的 index 会覆盖前面的
        }
        for(int i = 0; i < nums.length; i++){
            int n = target - nums[i];
            if(map.containsKey(n) && map.get(n) != i){//不能重复使用一个。如果有duplicate，这里找到的会是最后出现的点，而这里的 i 是最开始的
                return new int[]{i, map.get(target - nums[i])};
            }
        }
        return new int[2];
    }
}



//-------1 ROUND-----------------------------------------
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> numsMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            if(numsMap.containsKey(target - nums[i])){
                result[0] = numsMap.get(target - nums[i]);
                result[1] = i;
                return result;
            }else{
                numsMap.put(nums[i], i);
            }
        }
        return result;
    }
}
class Solution {
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        map.put(nums[i], i);
    }
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement) && map.get(complement) != i) {
            return new int[] { i, map.get(complement) };
        }
    }
    throw new IllegalArgumentException("No two sum solution");
}
}



/*
Solution 1: 粗暴遍历
RT=52ms
时间复杂度=O(n2)

Notes:
1.java基本语句很不熟悉，要看构造数组方法？
2.要看break之类的解法，能够提前退出循环？
3.要看对于return的位置?
4.判断值的大小关系要 ==
5.注意twosum的位置要在if里面，除非能够准时终止for loop
6.返回类型为数组，则最好在函数最外层创建一个临时数组，以便返回值
7.注意审题: 数组的值 or 数组的序号
8.还要看其他更快的解法?
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] twosum=new int[2];
        for(int i=0; i<nums.length; i++)
        {
            
            for(int j=i+1; j<nums.length; j++){
                if(nums[i]+nums[j]==target){
                    twosum[0]=i;
                    twosum[1]=j;
                    
                    break;
                }
            }
                       
        }
        return twosum;
    }
}

