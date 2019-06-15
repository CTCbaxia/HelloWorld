/*
405. Convert a Number to Hexadecimal

*/
/*
非常笨的 Brute Force
num == 0, return "0"
num > 0, 正数直接转换
num < 0, 
1) 先对该数取负数
2) 对该数补全到 8 位
3) 对该数取 16 进制的反
4) 对该 16 进制做 +1 运算


需要 16 进制 和 10 进制符号相互转换的对应（map,h）
****对于 -num 这种情况，要考虑 Integer.MIN_VALUE 的溢出
*/
class Solution {
    char[] h = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    Map<Character, Integer> map = new HashMap<>();
    
    
    public String toHex(int num) {
        for(int i = 0; i < 16; i++){
            char c;
            if(i > 9){
                c = (char)(i - 10 + 'a');
            }else{
                c = (char)(i + '0');
            }
            map.put(c, i);
        }
        if(num == 0) return "0";
        else if(num < 0){
            //get hex for -num
            String addup = "1";
            if(num == Integer.MIN_VALUE){
                num += 1;
                addup = "0";
            } 
            String res = toHex(-num);
            //补全
            while(res.length() < 8){
                res = '0' + res;
            }
            //取反
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < res.length(); i++){
                char c1 = res.charAt(i);
                sb.append(h[15 - map.get(c1)]);
            }
            // +1
            return add(sb.toString(), addup, map);
        }
        else{
            String res = "";
            while(num != 0){
                res = h[num % 16] + res;
                num /= 16;
            }
            return res;
        }
    }
    private String add(String n1, String n2, Map<Character, Integer> map){
        StringBuilder sb = new StringBuilder();
        int i1 = n1.length() - 1, i2 = n2.length() - 1;
        int carry = 0;
        while(i1 >= 0 || i2 >= 0){
            int d1 = (i1 >= 0) ? map.get(n1.charAt(i1)) : 0;
            int d2 = (i2 >= 0) ? map.get(n2.charAt(i2)) : 0;
            int num = d1 + d2 + carry;
            sb.append(h[num%16]);
            carry = num/16;
            
            i1--;
            i2--;
        }
        if(carry > 0 && sb.length() < 8){
                sb.append(h[carry]);
        }
        int index = sb.length() - 1;
        while(index >= 0 && sb.charAt(index) == '0'){
            sb.deleteCharAt(index--);
        }
        return sb.reverse().toString();
    }
}