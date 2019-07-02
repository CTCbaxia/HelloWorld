/*
414. Third Maximum Number
*/
/*
Three marker + check duplicate
tips: using Integer and initialization = null

Time: O(n)
Space: O(1)

fail:[1,-2147483648,2]
*/
class Solution {
    public int thirdMax(int[] nums) {
        Integer first = null, second = null, third = null;
        for(Integer num : nums){//should be Integer
            if(num.equals(first) || num.equals(second) || num.equals(third)) continue;//should compare this way
            
            if(first == null || num > first){
                third = second;
                second = first;
                first = num;
            }else if(second == null || (num > second && num < first)){
                third = second;
                second = num;
            }else if(third == null || (num > third && num < second)){
                third = num;
            } 
        }
        return third == null ? first:third;
    }
}

/*
Three marker + check duplicate
Time: O(n)
Space: O(1)

fail:[1,-2147483648,2]
*/

class Solution {
    public int thirdMax(int[] nums) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE, third = Integer.MIN_VALUE;//对于可能有 Integer.MIN_VALUE 值的情况，不要用这个来初始化
        int  count = 0;
        for(int num : nums){
            
            if(num > first){
                third = second;
                second = first;
                first = num;
                count++;
            }else if(num > second && num != first){
                third = second;
                second = num;
                count++;
            }else if(num >= third && num != first && num != second){//for [1,-2147483648]
                third = num;
                count++;
            } 
        }
        return count >= 3 ? third : first;
    }
}
