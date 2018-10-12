/*
8. String to Integer (atoi)
https://leetcode.com/problems/string-to-integer-atoi/description/
*/
class Solution {
    public int myAtoi(String str) {
        str = str.trim();
        char[] c = str.toCharArray();
        int sign = 1;
        int res = 0;
        for(int i = 0; i < c.length; i++){
            if(i == 0 && (c[i] == '+' || c[i] == '-')){
                if(c[i] == '-') sign = -1;
                continue;
            }
            if(!Character.isDigit(c[i])) break;
            System.out.println(res);
            if(res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && ((sign == 1) ? c[i] - '0' > 7 :  c[i] - '0'>8))){
                return (sign == 1) ? Integer.MAX_VALUE:Integer.MIN_VALUE;
            } 
            res = res * 10 + c[i] - '0';
        }
        
        return res * sign;
    
    }
}


"-13+8"
"-   12"
"-23dd23"
"maxint"
"       +kkk8"
"-3924x8fc" - 括号数量不对。而且这里可以不要 sign=-1	 的，因为当num=8/9的时候，都是返回max，负数的max值也就是8
                if( (result > Integer.MAX_VALUE / 10) || (result == Integer.MAX_VALUE / 10 && (( num > Integer.MAX_VALUE % 10 && sign==1) ||( num > Integer.MAX_VALUE % 10+1 && sign==-1)) ))


class Solution {
    public int myAtoi(String str) {
        int sign = 1;
        int count = 0;
        boolean start = false;
        int result = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i)==' '){
                if(!start && count==0){
                    continue;
                }else{
                    break;
                }
            }
            
            if((str.charAt(i) < '0' || str.charAt(i) > '9')){
                
                if(str.charAt(i) == '+' && !start){
                    count++;
                    if(count > 1){
                        break;
                    }else{
                        sign = 1;
                    }
                }else if(str.charAt(i) == '-' && !start){
                    count++;
                    if(count > 1){
                        break;
                    }else{
                        sign = -1;
                    }
                }else{
                    break;
                }
                
            }
            if(str.charAt(i) >= '0' && str.charAt(i) <='9'){
                start = true;
                int num = str.charAt(i) - '0';//字符串变整型
                if( (result > Integer.MAX_VALUE / 10) || (result == Integer.MAX_VALUE / 10 && (( num > Integer.MAX_VALUE % 10 && sign==1) ||( num > Integer.MAX_VALUE % 10+1 && sign==-1)) )){
                    return sign == 1 ? Integer.MAX_VALUE:Integer.MIN_VALUE;
                }
                result = result * 10 + num;
            }
        }
        
        result = result * sign;
        return result;
    }
}

