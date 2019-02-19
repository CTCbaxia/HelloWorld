/*
163. Missing Ranges
*/

/*
Two Pointers
注意 check min max int 的情况

Time: O(n)
Space: O(1)
*/
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        int lo = lower;
        
        for(int i = 0; i < nums.length; i++){
            int hi = nums[i];
            if(hi != Integer.MIN_VALUE){
                if(lo <= hi - 1) result.add(getRange(lo, hi - 1));
            }
            if(hi < Integer.MAX_VALUE) lo = hi + 1;//lo 不能越界
            else lo = hi;
        }
        if(lo != Integer.MAX_VALUE && lo <= upper) result.add(getRange(lo, upper));
        
        return result;
    }
    private String getRange(int lo, int hi){
        if(lo == hi){//upper may not exist in nums
            return Integer.toString(lo);
        }else{
            return Integer.toString(lo) + "->" + Integer.toString(hi);
        }
    }

}




/*
Two Pointers
注意 check min max int 的情况
[-2147483648, -1, 0, 2, 2147483647]
[-2147483648 -- 2147483647]

Time: O(n)
Space: O(1)
*/
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        int lo = lower;
        
        for(int i = 0; i < nums.length; i++){
            int hi = nums[i];
            if(hi != Integer.MIN_VALUE && lo == hi - 1){//hi - 1 不能越界 && if lo 和 hi 之间只有一个数字
                result.add(Integer.toString(lo));
            }else if(hi != Integer.MIN_VALUE && lo < hi - 1){
                result.add(Integer.toString(lo) + "->" + Integer.toString(hi - 1));
            }
            if(hi < Integer.MAX_VALUE) lo = hi + 1;//lo 不能越界
            else lo = hi;
        }
        if(lo == upper && lo != Integer.MAX_VALUE){//upper may not exist in nums
            result.add(Integer.toString(lo));
        }else if(lo < upper){
            result.add(Integer.toString(lo) + "->" + Integer.toString(upper));
        }
        return result;
    }

}




