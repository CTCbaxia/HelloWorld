/*
67. Add Binary
https://leetcode.com/problems/find-pivot-index/description/

TIME: 0719 - 1h
RESULT: 98% - 2ms
NOTES:
0. Computation from string usually can be simplified by using a [carry] as such.

1. StringBuilder Method
    - sb.append(something)
    - sb.toString();
    - sb.reverse().toString();  可以省去用 stack 的方式

2. 按位加和的规律
    - sum = na + nb + carry
    - this digit = sum % 2  算奇偶
    - carry = sum / 2       算大小（compared with 1）

3. 将 stack 全部导出，更简洁的方法：
    while(!stack.isEmpty()) sb.append(stack.pop());

METHOD:
对位，按位加和
*/
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1; 
        int j = b.length() - 1;
        int carry = 0;
        while(i >= 0 || j >= 0){
            int sum = carry;
            if(i >= 0) sum = sum + (a.charAt(i) - '0');
            if(j >= 0) sum = sum + (b.charAt(j) - '0');
            sb.append(sum % 2);
            carry = sum / 2;
            i--; j--;
        }
        if(carry == 1) sb.append(carry);
        return sb.reverse().toString();
    }
}


/*
TIME: 0719 - 30min
RESULT: 47% - 5ms
*/

class Solution {
    public String addBinary(String a, String b) {
        Stack<Integer> res = new Stack<Integer>();
        String longer = a;
        String shorter = b;
        if(a.length() < b.length()){
            longer = b;
            shorter = a;
        }
        int min = shorter.length();
        int addup = 0;
        for(int i = 0; i < longer.length(); i++){
            int nl = longer.charAt(longer.length() - 1 - i) - '0';
            int ns = 0;
            if(i < min) ns = shorter.charAt(shorter.length() - 1 - i) - '0';  
            int sum = nl + ns + addup;
            if(sum <= 1 ){
                res.push(sum);
                addup = 0; 
            }else if(sum >= 2){
                res.push(sum % 2);
                addup = 1;
            }
        }
        if(addup == 1) res.push(addup);
        
        StringBuilder sb = new StringBuilder();
        int length = res.size();
        for(int i = 0; i < length; i++) sb.append(res.pop());
        return sb.toString();
    }
}


