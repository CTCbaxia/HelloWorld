/*
EASY
268. Missing Number

RESULT: 
NOTES: 
1. Calculate the sum
2. Bit Manipulation
3. Put to right poristion
4. Sort and Binary Search
*/


/*
Calculate Sum:

get the target addup target sum
get the actual sum
find (target - actual)

Time: O(n)
Space: O(1)
*/
class Solution {
    public int missingNumber(int[] nums) {
        int sum = 0, n = nums.length;
        int target = n * (n + 1)/2;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        return target - sum;
    }
}

/*
Bit Manipulation:

xor all index and xor all nums[i]
Then see the missing number

Time: O(n)
Space: O(1)
*/

class Solution {
    public int missingNumber(int[] nums) {
        int xor = nums.length;
        for(int i = 0; i < nums.length; i++){
            xor ^= i ^ nums[i];//for existing i, it will be zero
        }
        return xor;
    }
}


/*
Put to right poristion: 

0-n keys
array with length == n
put the number at the right position

Time: O(n)
Space: O(1)
*/
class Solution {
    public int missingNumber(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i){
                //swap nums[i] to the nums[i] index
                int index = nums[i];
                if(index >= nums.length) continue;
                nums[i] = nums[index];
                nums[index] = index;
                i--;
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i) return i;
        }
        return nums.length;
    }
}

/*
Binary Search:
if input is sorted
find the miss

Time: O(nlogn)排序 + O(logn)查找
Space: O(1)
*/
class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int lo = 0, hi = nums.length;//mid will not be hi
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(nums[mid] > mid) hi = mid;
            else lo = mid + 1;
        }
        return hi;//result can be nums.length
    }
}
// sorting
class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int index = 0;
        while(index < nums.length){
            if(nums[index] != index) return index;
            index++;
        }
        return index;
    }
}