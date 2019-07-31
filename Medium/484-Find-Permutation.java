/*
M
484. Find Permutation
*/
/*
Swap for reverse order
这种递增递减的题首先想到 swap

I - inorder
D - reverse order

123456
 IDDII
143256
 ---reverse

Time: O(n)
Space: O(1)
*/
class Solution {
    public int[] findPermutation(String s) {
        int[] res = new int[s.length() + 1];
        //initialization
        for(int i = 0; i < res.length; i++){
            res[i] = i + 1;
        }
        //build res
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == 'D'){
                int start = i;
                while(i < s.length() && s.charAt(i) == 'D'){
                    i++;
                }
                swapWindow(res, start, i);
                i--;//move back one step
            }
        }
        return res;
        
    }
    private void swapWindow(int[] nums, int start, int end){
        while(start < end){
            int tmp = nums[end];
            nums[end] = nums[start];
            nums[start] = tmp;
            start++;
            end--;
        }
    }
}
