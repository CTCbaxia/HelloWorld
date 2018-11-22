/*
283. Move Zeroes
https://leetcode.com/problems/move-zeroes/description/

TIME: 0804 - 30min
RESULT: 100% - 1ms

*/
/*
类似于 quick sort
pointer指向需要放 nonzero 的点

Time: O(n)
Space: O(1)
*/
class Solution {
    public void moveZeroes(int[] nums) {
        int pointer = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                int tmp = nums[i];
                nums[i] = nums[pointer];
                nums[pointer] = tmp;
                pointer++;
            }
        }
        return;
    }
}

/*
1 pointer

把非零值往前移，后面附值为0

Time: O(n)
Space: O(1)
*/
class Solution {
    public void moveZeroes(int[] nums) {
        int pointer = 0;
        
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0) nums[pointer++] = nums[i];
        }
        for(int i = pointer; i < nums.length; i++){
            nums[pointer++] = 0;
        }
        return;
    }
}

/*
Two pointers + swap

index 指向要放 nonzero的点
i loop nums

Time: O(n)
Space: O(1)
*/
class Solution {
    public void moveZeroes(int[] nums) {
        int index = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                if(i != index){
                    int tmp = nums[i];
                    nums[i] = nums[index];
                    nums[index] = tmp;                    
                }
                index++;//不管 i 是否等于 index, index 这里肯定放了一个 nonzero，需要指向下一个要放 nonzero的点
            }
        }
        return;
    }
}





