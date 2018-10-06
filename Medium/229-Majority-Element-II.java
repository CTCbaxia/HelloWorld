/*
MEDIUM
229. Majority Element II
https://leetcode.com/problems/majority-element-ii/description/

TIME: 10.6 - 1h
RESULT: 14% - 14ms

*/
/*
SOLUTION REFERENCE: Boyer-Moore Majority Vote
The essential concepts is you keep a counter for the majority number X. 
If you find a number Y that is not X, the current counter should deduce 1. 
The reason is that if there is 5 X and 4 Y, there would be one (5-4) more X than Y. This could be explained as "4 X being paired out by 4 Y".

If there are more elements other than X, then it must not be the most

And since the requirement is finding the majority for more than ceiling of [n/3], the answer would be less than or equal to two numbers.
So we can modify the algorithm to maintain two counters for two majorities.

*/
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        if(nums.length == 0 || nums == null) return new ArrayList<Integer>();
        //there will be at most 2 elements
        int number1 = nums[0], number2 = nums[0], count1 = 0, count2 = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == number1) count1++;
            else if(nums[i] == number2) count2++;
            else if(count1 == 0){
                number1 = nums[i];
                count1++;
            }
            else if(count2 == 0){
                number2 = nums[i];
                count2++;
            }
            else{
                //any existing other element can make it less majority
                //if a number can be beated by elements other than the potential majority one, then it must not be the result
                count1--;
                count2--;
            }

        }
        count1 = 0;
        count2 = 0;
        for(int n : nums){
            if(n == number1) count1++;
            else if(n == number2) count2++;
        }
        List<Integer> res = new ArrayList<Integer>();
        if(count1 > nums.length / 3) res.add(number1);
        if(count2 > nums.length / 3) res.add(number2);
        return res;
    }
}
/*
SOLUTION 0:
Using map

*/
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> res = new ArrayList<Integer>();
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
            if(map.get(n) > nums.length / 3 && !res.contains(n)) res.add(n);
        }
        return res;
    }
}