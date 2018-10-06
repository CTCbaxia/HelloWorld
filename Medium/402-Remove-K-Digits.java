/*
MEDIUM
402. Remove K Digits
https://leetcode.com/problems/remove-k-digits/description/

TIME: 0916 - 1h
RESULT: 24% - 32ms
NOTES:
REFERENCE 思路不错
*/

//--Round 2 for MS-----------------------------------------------
/*
1 注意其实是可以字符串比较的
2 sb.append 进去后就都成为了 char，对应查询是 sb.charAt()，不管你的输入是什么

*/
class Solution {
    public String removeKdigits(String num, int k) {
        int remain = num.length() - k;
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while(remain > 0){
            int min = num.charAt(index);
            int tmp = index;
            for(int i = index; i < num.length() - remain + 1; i++){
                int number = num.charAt(i);
                if(number < min){
                    min = number;
                    tmp = i;
                }
            }
            index = tmp + 1;
            remain--;
            sb.append(num.charAt(tmp));
        }
        
        while(sb.length() != 0 && sb.charAt(0) == '0'){
            sb.deleteCharAt(0);
        }
        return (sb.length() == 0) ? "0":sb.toString();
    }
}
/*
思路：
we use a stack to store all the integers, but if when we input an integer but found the current integer is smaller than the peek one in the stack, and 
if we still can remove elements(k > 0), we remove the peek. we do iteratively until we cannot remove something or we found the thing smaller than the current insert elements


remember to check if you have removed all k elements because you may found there is nothing to remove (you only remove elements when you meet slope.)
*/

class Solution {
    public String removeKdigits(String num, int k) {
        if(num.length() == 0) return num;
        
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < num.length(); i++){
            while(k > 0 && !stack.isEmpty() && num.charAt(i) < stack.peek()){
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
        }
        while(k > 0){
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        sb.reverse();
        while(sb.length() != 0 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}




//--Round 1-----------------------------------------------
/*
SOLUTION REFERENCE:
TIME: 0916 - 1h
RESULT: 24% - 32ms
METHOD:
每次如果后面的数小于stack前面存的数，而删除名额又没用完，就删除前面的数。注意这里用 while。这样可以完美解决 12300021 k = 3 的这种情况
*/
class Solution {
    public String removeKdigits(String num, int k) {
        if(k == num.length()) return "0";
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < num.length(); i++){
            while(!stack.isEmpty() && stack.peek() > num.charAt(i) && k > 0){
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
        }
        //remove case like "1111"
        while(k-- > 0) stack.pop();
        
        //build string
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) sb.append(stack.pop());
        sb.reverse();
        
        //remove leading zero
        while(sb.length() > 1 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.toString();
    }
}

/*
SOLUTION 0:
TIME: 0916 - 1h
RESULT: 21% - 36ms
在 firstIndex ~ lastIndex (始终保持 lastIndex 后面剩余的 digits 数量满足留存需求) 中找最小值，如果有多个，就找最前面的。即每次在区间内找最小值。
hint: k 到 num.length() 的距离，就是要留的 digits 数量
*/

class Solution {
    public String removeKdigits(String num, int k) {
        if(k == 0){
            int start = 0;
            while(start < num.length() && num.charAt(start) == '0') start++;
            return start == num.length() ? "0" : num.substring(start);
        }
        if(k == num.length()) return "0";
        int lastIndex = k;
        int firstIndex = 0;
        StringBuilder sb = new StringBuilder();
        while(lastIndex < num.length()){
            char min = num.charAt(firstIndex);
            //find the min in the substring
            for(int i = firstIndex; i <= lastIndex; i++){
                if(num.charAt(i) < min) min = num.charAt(i);
            }
            //find the min position
            firstIndex = num.indexOf(min, firstIndex);
            if(min == '0' && firstIndex <= k){
                if(firstIndex == num.length() - 1) return "0";
                return removeKdigits(num.substring(firstIndex + 1), k - firstIndex);
            }
            sb.append(min);
            firstIndex++;
            lastIndex++;
        }
        return sb.toString();
    }
}



//思路错了
//if k > digits before zero, then remove all digits before zero. And for the left, do remove(k - numdigits)Digits again.
//if k > num.length() return 0
//normal case, choose the smallest n - k digits
class Solution {
    public String removeKdigits(String num, int k) {
        if(k == 0) return num;
        if(k == num.length()) return "0";
        //deal with leading zero
        
        int[] digits = new int[10];
        for(int i = 0; i < num.length(); i++){
            int n = num.charAt(i) - '0';
            if(n == 0 && i <= k){
                if(i == num.length() - 1) return "0";
                else return removeKdigits(num.substring(i + 1), k - i);
            }
            digits[n] += 1;
        }
        for(int i = 9; i >= 0; i--){
            if(digits[i] <= k){
                k-= digits[i];
                digits[i] = 0;
            }else{
                digits[i] -= k;
                k = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = num.length() - 1; i>= 0; i--){
            int n = num.charAt(i) - '0';
            if(digits[n] == 0) continue;
            sb.append(n);
            digits[n]--;
        }
        return sb.toString();
    }
}