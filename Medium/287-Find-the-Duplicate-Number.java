/*
MEDIUM
287. Find the Duplicate Number
https://leetcode.com/problems/find-the-duplicate-number/description/

TIME: 0904 - 30min
RESULT: 100% - 1ms
NOTES:
*/
/*
Time: O(nlogn)
Space: O(1)

计算：
T(n) = T(n / 2) + c (c = n 但是因为这个值不随迭代改变，所以用恒量表示)
a = 1, b = 2, n^logba = 1, f(n) = c
case 2 => O(clogn) = O(nlogn)
*/
class Solution {
    public int findDuplicate(int[] nums) {
        int lo = 1, hi = nums.length - 1;
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            int lower = 0;
            
            for(int i : nums){
                if(i <= mid) lower++;
            }
            if(lower > mid) hi = mid;
            else lo = mid + 1;
        }
        return hi;//lo == hi
    }
}

/*
Treatd the array as a linked list with a circle
the number of that index is the next node
 0,1,2,3,4
[1,3,4,2,2] = 0 -> 3 -> 4 -> 2 ->3

Time: O(n)
Space: O(1)
*/
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[slow];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;//让 fast 回到置零，然后和 slow 一起走 list。fast 的第一步一定要和 slow 一起迈，不然死循环
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }
}









//sort : no you cannot modify the array
class Solution {
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == nums[i - 1]) return nums[i];
        }
        return -1;
    }
}

//two for loop 直接判断相等不就够了
class Solution {
    public int findDuplicate(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                if((nums[i] == nums[j])) return nums[i];
            }
        }
        return -1;
    }
}

/*
如果可以modify
可以 point 到 0 的位置，把 element swap 到合适的 index，如果该 index 已经有值，就重复
*/


//two pointers
//lo hi 指的是真实的值，而不是 index

class Solution {
    public int findDuplicate(int[] nums) {
        int lo = 1;
        int hi = nums.length - 1;
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            int lower = 0;
            for(int n : nums){
                if(n <= mid) lower++;
            }
            if(lower > mid) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}
/*
SOLUTION 0: 区域判断
TIME: 0904 - 30min
RESULT: 48% - 3ms
*/
class Solution {
    public int findDuplicate(int[] nums) {
        int lo = 1, hi = nums.length - 1;//n + 1 = nums.length
        while(lo < hi){
            int mid = (lo + hi) / 2;
            int lower = 0;
            for(int n : nums){
                if(n <= mid) lower++;
            }
            if(lower > mid) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}
/*
Wrong Code: [1,3,2,2] 会死循环。
因为 lo = mid 很有可能是一个没有任何改变的值。int mid = (lo + hi) / 2; 本来就可能有 lo = mid
*/
class Solution {
    public int findDuplicate(int[] nums) {
        int lo = 1, hi = nums.length - 1;//n + 1 = nums.length
        while(lo < hi){
            int mid = (lo + hi) / 2;
            int lower = 0;
            for(int n : nums){
                if(n < mid) lower++;
            }
            if(lower >= mid) hi = mid - 1;
            else lo = mid;
        }
        return lo;
    }
}

//time : O(n^2)
//space: O(1)
class Solution {
    public int findDuplicate(int[] nums) {
        
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[j] == nums[i]) return nums[i];
            }
        }
        return -1;
    }
}