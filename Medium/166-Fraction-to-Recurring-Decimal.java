/*
MEDIUM
166. Fraction to Recurring Decimal
https://leetcode.com/problems/fraction-to-recurring-decimal/description/

TIME: 0908 - 1h
RESULT: 11% - 7ms（方法和最快的一样）
*/
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        long numer = (long) numerator;
        long deno = (long) denominator;
        Map<Long, Integer> map = new HashMap<Long, Integer>();//<余数值，对应位置>
        
        if(numer < 0 && deno > 0 || numer > 0 && deno < 0){
            sb.append("-");
            numer = Math.abs(numer);
            deno = Math.abs(deno);
        }
        long left = numer / deno;
        long num = numer % deno;
        
        sb.append(left);

        if(num != 0) sb.append(".");
        int point = sb.length() - 1;
        map.put(num, ++point);
        
        while(num != 0){
            point++;
            long divider = num * 10 / deno;
            long remaining = num * 10 % deno;
            if(!map.containsKey(remaining)) map.put(remaining, point);
            else{
                int repeat = map.get(remaining);
                sb.append(divider);
                sb.insert(repeat, "(");
                sb.append(")");
                break;
            }

            sb.append(divider);
            num = remaining;
            
        }
        return sb.toString();
    }
}

Input:
-50
8
Output:
"-6.-2-5"
Expected:
"-6.25"



Input:
-1
-2147483648
Output:
"0.0000000000000000000000000000001"
Expected:
"0.0000000004656612873077392578125"


//更简化一点
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        long numer = (long) numerator;
        long deno = (long) denominator;
        Map<Long, Integer> map = new HashMap<Long, Integer>();//<余数值，对应位置>
        
        if(numer < 0 && deno > 0 || numer > 0 && deno < 0){
            sb.append("-");
            numer = Math.abs(numer);
            deno = Math.abs(deno);
        }
        
        
        sb.append(numer / deno);//left part 
        long num = numer % deno;
        
        if(num != 0) sb.append(".");
        map.put(num, sb.length());
        
        while(num != 0){
            long divider = num * 10 / deno;
            long remaining = num * 10 % deno;
            sb.append(divider);
            
            if(!map.containsKey(remaining)) map.put(remaining, sb.length());
            else{
                int repeat = map.get(remaining);
                sb.insert(repeat, "(");
                sb.append(")");
                break;
            }
            num = remaining;
        }
        return sb.toString();
    }
}