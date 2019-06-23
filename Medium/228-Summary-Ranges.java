/*
MEDIUM
228. Summary Ranges

*/
/*
Loop and check - simplify to avoid the check at the end
Time: O(n)
Space: O(1)
*/
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        
        for(int i = 0; i < nums.length; i++){
            int start = nums[i];
            while(i + 1 < nums.length && nums[i] + 1 == nums[i + 1]){
                i++;
            }
            if(start == nums[i]) res.add(String.valueOf(start));
            else{
                res.add(String.valueOf(start) + "->" + String.valueOf(nums[i]));
            }
        }

        return res;
    }
}

/*
Loop and check
Time: O(n)
Space: O(1)
*/
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        
        int start = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == nums[i - 1] + 1) continue;
            
            if(start == nums[i - 1]) res.add(String.valueOf(start));
            else{
                res.add(String.valueOf(start) + "->" + String.valueOf(nums[i - 1]));
            }
            start = nums[i];
        }
        //check end
        if(start == nums[nums.length - 1]) res.add(String.valueOf(start));
        else res.add(String.valueOf(start) + "->" + String.valueOf(nums[nums.length - 1]));
        return res;
    }
}