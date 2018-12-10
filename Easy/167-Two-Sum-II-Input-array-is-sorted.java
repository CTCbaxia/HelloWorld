/*
EASY
167. Two Sum II - Input array is sorted

TIME: 1007 -  10min
RESULT: 100% - 0ms
NOTES: 
2 Sum 其实也可以用排序的方法

*/
/*
Two Pointers

Time: O(n)
Space: O(1)
*/
class Solution {
    public int[] twoSum(int[] numbers, int t){
        int lo = 0, hi = numbers.length - 1;
        while(lo < hi){
            int sum = numbers[lo] + numbers[hi];
            if(sum < t) lo++;
            else if(sum > t) hi--;
            else return new int[]{lo + 1, hi + 1};
        }
        return null;
    } 
}



class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int lo = 0, hi = numbers.length - 1;
        while(lo < hi){
            int sum = numbers[lo] + numbers[hi];
            if(sum == target) return new int[]{lo + 1, hi + 1};
            else if(sum > target) hi--;
            else lo++;
        }
        return null;
    }
}