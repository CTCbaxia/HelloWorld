/*
EASY
532. K-diff Pairs in an Array

*/
/*
HashMap to check
we know exactly what will be the matched value, so use hash map

Time: O(n)
Space: O(n)
*/
class Solution {
    public int findPairs(int[] nums, int k) {
        if(k < 0) return 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        int count = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(k == 0){
                if(entry.getValue() >= 2) count++;
            }else{
                if(map.containsKey(entry.getKey() + k)) count++;
            }
        }
        return count;
    }
}




/*
Sort the array to get Unique Pair
And check for each pair

Time: O(nlogn)
Space: O(1)
*/
class Solution {
    public int findPairs(int[] nums, int k) {
        int res = 0;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                while(j + 1 < nums.length && nums[j + 1] == nums[j]){
                    j++;
                }
                if(nums[j] - nums[i] == k) res++;
            }
            while(i + 1 < nums.length && nums[i + 1] == nums[i]){
                i++;
            }
        }
        return res;
    }
}