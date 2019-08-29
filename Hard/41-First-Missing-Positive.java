/*
HARD
41. First Missing Positive

1. 返回值一定在 1 ~ nums.length + 1 里面
2. swap 之后遍历的时候没有匹配的元素的 index+1就是需要的结果
*/
/*
swap to right place(n -> n-1 position)

**
if the swap nums are the same, skip the swap

Time: O(n)
Space: O(1)
*/
class Solution {
    public int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            //if not in range, just skip
            if(nums[i] > 0 && nums[i] <= nums.length && nums[i] != i + 1){//in range but not at right pos
                int j = nums[i] - 1;//should be at j
                if(nums[j] == nums[i]) continue;//if nums[i] is already at right pos
                swap(nums, i, j);//put nums[i] to the right pos
                i--;//recheck this index
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i + 1) return i + 1;
        }
        return nums.length + 1;
    }
    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

//SOLUTION REFERENCE:
//in place
class Solution {
    public int firstMissingPositive(int[] nums) {
        if(nums.length == 0) return 1;
        
        for(int i = 0; i < nums.length; i++){
            int n = nums[i];
            if(n <= 0) continue;
            if(n > 0 && n <= nums.length && n != i + 1){
                //swap the elements to put it to right place nums[nums[i] - 1]
                if(nums[n - 1] == n) continue;
                int tmp = nums[n - 1];
                nums[n - 1] = n;
                nums[i] = tmp;
                i--;
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i + 1) return i + 1;
        }
        return nums.length + 1;
    }
}

//SOLUTION REFERENCE:
//需要 extra space
class Solution {
    public int firstMissingPositive(int[] nums) {
        if(nums.length == 0) return 1;
        int[] match = new int[nums.length + 2];
        
        for(int i : nums){
            if(i <= 0) continue;
            if(i > 0 && i <= nums.length + 1) match[i] = i;
        }
        for(int i = 1; i < match.length; i++){
            if(match[i] != i) return i;
        }
        return 1;
    }
}



class Solution {
    public int firstMissingPositive(int[] nums) {
        if(nums.length == 0) return 1;
        Arrays.sort(nums);
        int pre = nums[0];
        if(pre > 1 || nums[nums.length - 1] < 0) return 1;
        
        for(int i = 1; i < nums.length; i++){
            int cur = nums[i];
            if(cur > 1 && cur - pre > 1) return pre < 0 ? 1 : pre + 1;
            pre = cur;
        }
        return pre + 1;
    }
}