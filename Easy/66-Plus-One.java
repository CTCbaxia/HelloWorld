/*
66. Plus One
*/
/*
数学相加的 tricky 版本
只需要判断是不是 9
如果需要进一位，那一定是类似于 999...
所以只需要新建一个首位为 1 的长度 + 1 的数组，其余默认 0

Time: O(n)
Space: O(1)
*/
class Solution {
    public int[] plusOne(int[] digits) {
        for(int i = digits.length - 1; i >= 0; i--){
            if(digits[i] < 9){
                digits[i]++;
                return digits;
            }
            digits[i] = 0;//add one for this digit, need to add one for the left one
        }
        //if all finished and still need to add one, then must be 9999...
        int[] nums = new int[digits.length + 1];
        nums[0] = 1;
        return nums;
    }
}



/*
数学相加(carry)

Time: O(n)
Space: O(n)
*/
class Solution {
    public int[] plusOne(int[] digits) {
        List<Integer> res = new ArrayList<>();
        int carry = 1;
        for(int i = digits.length - 1; i >= 0; i--){
            int num = carry + digits[i];
            carry = num / 10;
            num = num % 10;
            res.add(0, num);
        }
        if(carry > 0) res.add(0, carry);
        int[] result = new int[res.size()];
        for(int i = 0; i < result.length; i++){
            result[i] = res.get(i);
        }
        return result;
    }
}  





class Solution {
    public int[] plusOne(int[] digits) {
        int length = digits.length;
        int[] result = plus(digits, length - 1);
        return result;
    }
    private int[] plus(int[] digits, int index){
        if(digits[index] % 10 != 9){
            digits[index] = digits[index] + 1;
            return digits;
        }else{
            digits[index] = 0;
            if(index > 0){
                digits = plus(digits, index - 1); 
            }else{
                int[] newdigits = new int[digits.length + 1];
                newdigits[0] = 1;
                for(int i = 1; i < newdigits.length; i++){
                    newdigits[i] = digits[i - 1];
                }
                return newdigits;
            }
            
        }
        return digits;
    }
}
