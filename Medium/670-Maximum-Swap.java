/*
MEDIUM
670. Maximum Swap
*/
/*
Array + generate "index of the Largest num after me (included)" Array + swap
Time: O(n)
Space: O(n)


Solution 2:
Check for each one to find the largest number after it and see if they can swap
end when having make one swap

Time: O(n^2)
Space: O(1)
*/
class Solution {
    public int maximumSwap(int num) {
        //turning num into an array
        char[] nums = String.valueOf(num).toCharArray();
        
        //generate the "index of the Largest num after me" array
        int[] largest = new int[nums.length];
        int index = nums.length - 1;
        for(int i = nums.length - 1; i >= 0; i--){
            int curNum = nums[i] - '0';
            int curLargest = nums[index] - '0';
            if(curNum > curLargest) index = i;//需要找到最远的，最大于(等于)它的点。而非最近的
            largest[i] = index;
        }
        
        //compare and swap
        for(int i = 0; i < nums.length - 1; i++){
            int curNum = nums[i] - '0';
            int curLargest = nums[largest[i]] - '0';
            if(curNum < curLargest){//如果确实大于他
                swap(nums, i, largest[i]);
                return Integer.valueOf(new String(nums));
            } 
        }
        return num;    
        
    }
    private void swap(char[] nums, int i, int j){
        char tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}





/*
Array + generate Largest after me Array + swap
Time: O(n)
Space: O(n)

Solution 2:
Check for each one to find the largest number after it and see if they can swap
end when having make one swap

Time: O(n^2)
Space: O(1)
*/
class Solution {
    public int maximumSwap(int num) {
        //turning num into an array
        int[] digits = new int[9];
        int index = digits.length - 1;
        while(num > 0){
            digits[index--] = num % 10;
            num /= 10;
        }
        
        //generate the "largest number after it" array
        //***(Wrong) End for loop when digits[i] == 0, otherwise swap will happen at the 0th number
        //***Should mark the first index
        int[] largest = new int[9];
        int firstIndex = digits.length - 1;
        index = -1;
        for(int i = largest.length - 1; i >= 0; i--){
            largest[i] = index;
            
            if(index == -1) index = i;//❌ 这个解法不好，因为对 881 这种会生成: 2,2,-1。事实上没有一个对应的数是大于原数的。违背了largest本意
            else{
                if(digits[i] > digits[index]) index = i;
            }
            //update the first index
            if(digits[i] > 0) firstIndex = i;
        }
        
        //compare and swap
        for(int i = firstIndex; i < digits.length && largest[i] >= 0; i++){
            if(digits[largest[i]] > digits[i]){
                swap(digits, i, largest[i]);
                break;
            } 
        }
        
        //turn the digits into int
        int res = 0;
        for(int i = 0; i < digits.length; i++){
            res = res * 10 + digits[i];
        }
        return res;
    }
    private void swap(int[] digits, int i, int j){
        int tmp = digits[i];
        digits[i] = digits[j];
        digits[j] = tmp;
    }
}





/*
❌ 错误示范
Array + generate "index of the Largest num after me (included)" Array + swap
Time: O(n)
Space: O(n)


Solution 2:
Check for each one to find the largest number after it and see if they can swap
end when having make one swap

Time: O(n^2)
Space: O(1)
*/
class Solution {
    public int maximumSwap(int num) {
        //turning num into an array
        char[] nums = String.valueOf(num).toCharArray();
        
        //generate the "index of the Largest num after me" array
        int[] largest = new int[nums.length];
        int index = nums.length - 1;
        for(int i = nums.length - 1; i >= 0; i--){
            int curNum = nums[i] - '0';
            int curLargest = nums[index] - '0';
            if(curNum >= curLargest) index = i;//❌需要找到最远的，最大于它的点。而非最近的
            largest[i] = index;
        }
        
        //compare and swap
        for(int i = 0; i < nums.length - 1; i++){
            if(largest[i] > i){
                swap(nums, i, largest[i]);
                return Integer.valueOf(new String(nums));
            } 
        }
        return num;    
        
    }
    private void swap(char[] nums, int i, int j){
        char tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}