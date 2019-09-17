/*
MEDIUM
398. Random Pick Index

TIME: 
RESULT: 
NOTES:

*/
/*
https://leetcode.com/problems/random-pick-index/discuss/88072/Simple-Reservoir-Sampling-solution
At first, let's get clear that count is used to count the target number in nums. Say we now we have nums=[1,5,5,6,5,7,9,0] and the target is 5.

Now let's focus on the loop. When i=1, we get the first target number, and by rnd.nextInt(++count) we select a random number between [0, 1), which means actually you could only select 0, so the probability of making result = 1 is 1.

Keep going. In the loop where i = 2, we get the second number. Now we have to get a random number in {0,1}. So what should we do if we want to keep result = 1? It's simple: we have to make sure that, at this time, the random number generated should be 1 rather than 0 (otherwise the value of result will be changed), so the probability of keeping result = 1 is 1 * 1/2.

It is similar when we get the third target number, i.e., i = 4. Now we have to get a random number in {0,1,2}. If we still wish to keep result = 1, the only way is to randomly get number 1 or 2 rather than 0, and the probability is 2/3. So the total probability of keeping result = 1 will be 1 * 1/2 * 2/3.

Since we have four target number 5 here, the final probability of keeping result = 1 would be 1 * 1/2 * 2/3 * 3/4 = 1/4, which means the probability of picking index 0 is 1/4 as the problem required. The probability is the same if you wish to pick another index.

You may ask what is the probability of picking the last possible index 4? Well, it simpler. You may ignore all operations before the loop where i = 4, and the only thing you have to do is to get the random number 0 among {0,1,2,3} in the loop where i = 4, so the probability is exactly 1/4.
*/

/*
Reservoir Sampling solution:
找到一个就在 random 里面范围 + 1
当rand == 0 的时候才把他变成现在的数

Time: O(n)
Space: O(1)
*/
class Solution {
    int[] n;
    Random r;
    public Solution(int[] nums) {
        n = nums;
        r = new Random();
    }
    
    public int pick(int target) {
        int res = -1;
        int count = 0;
        for(int i = 0; i < n.length; i++){
            if(n[i] != target) continue;
            if(r.nextInt(++count) == 0) res = i;
            else res = res;//keep the last result, prob = 1 * 1/2 * 2/3 ...
        }
        return res;
    }
}


/*
Reservoir Sampling solution:
找到一个就在 random 里面范围 + 1
当rand == 0 的时候才把他变成现在的数

Time: O(n)
Space: O(1)
*/
class Solution {
    Random rand;
    int[] nums;
    public Solution(int[] nums) {
        rand = new Random();
        this.nums = nums;
    }
    
    public int pick(int target) {
        int index = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                count++;
                if(rand.nextInt(count) == 0) index = i;
            }
        }
        return index;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */



 
class Solution {
    int[] num;
    Random r;
    public Solution(int[] nums) {
        num = nums;
        r = new Random();
    }
    
    public int pick(int target) {
        int res = -1;
        int count = 1;
        for(int i = 0; i < num.length; i++){
            if(num[i] == target){
                int random = r.nextInt(count++);
                res = random == 0 ? i : res;
            }
        }
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */









