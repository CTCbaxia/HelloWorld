/*
26. Remove Duplicates from Sorted Array
Solution 2：beat 96%


思路：遍历数组，若遇到数>现有nums[lens-1]的，就把他移到nums[lens]来
*/

class Solution {
    public int removeDuplicates(int[] nums) {
        int index = 0; 
        int move = 0;
        while(move < nums.length){
            if(nums[move] > nums[index]){
                index++;
                int tmp = nums[index];
                nums[index] = nums[move];
                nums[move] = tmp;
                
            }
            move++;
        }
        return index+1;
    }
}





class Solution {
    public int removeDuplicates(int[] nums) {
        int lens;
        if(nums.length==0){
            lens=0;
            return lens;
        }else{
            lens=1;
        }
        int tmp=nums[0];
        for(int i=1;i<nums.length;i++){
            
            if(nums[i]>nums[lens-1]){ //其实这里只需要 nums[lens] = nums[i]; 因为不需要保证lens后面的数都是完整的
                int tmp_switch = nums[lens];
                nums[lens] = nums[i];
                nums[i] = tmp_switch;
                lens=lens+1;
            }else{
                continue;
            }
            
        }
        return lens;
            
    }
}


/*

类似的思路，非常简洁的写法

*/
public int removeDuplicates(int[] nums) {
    int i = 0;
    for (int n : nums)
        if (i == 0 || n > nums[i-1])
            nums[i++] = n;
    return i;
}
//And to not need the i == 0 check in the loop:

public int removeDuplicates(int[] nums) {
    int i = nums.length > 0 ? 1 : 0;
    for (int n : nums)
        if (n > nums[i-1])
            nums[i++] = n;
    return i;
}

/*
Solution 1: Time Limit Exceeded （在测试的最后一个步骤都是对的，但是超时了）
应该是对的，就是not efficient

思路：
遍历nums
如果nums[i]小于后一个数，则往下遍历
如果nums[i]等于后一个数，则把后一个数挪到最尾部
 */


class Solution {
    public int removeDuplicates(int[] nums) {
        int lens;
        if(nums.length==0){
            lens=0;
            return 0;
        }else{
            lens=1;//如果有数，肯定至少有一个lens
        }
   
        for(int i=0; i<nums.length-lens; i++){
            if(nums[i]<nums[i+1]){
                lens=lens+1;
                continue;
                
            }else{
                int tmp_num=nums[i+1];
                for (int j=0;j<nums.length-2-i;j++){
                    nums[i+1+j]=nums[i+2+j];
                }
                nums[nums.length-1]=tmp_num;
                i=i-1;
                    
            }
        }
        return lens;
    }
}