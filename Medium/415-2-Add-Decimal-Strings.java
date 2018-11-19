/*
来来 fb 面经：

Add Decimal
"6.43442"
"893.83"

Time: O(n)
Space: O(1)
*/
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        //find the point
        int p1 = 0, p2 = 0;
        while(num1.charAt(p1) != '.') p1++;
        while(num2.charAt(p2) != '.') p2++;
        
        //deal with longer part
        int i = num1.length() - 1, j = num2.length() - 1;
        while(i - p1 > j - p2) sb.append(num1.charAt(i--) - '0');
        while(j - p2 > i - p1) sb.append(num2.charAt(j--) - '0');
        
        //start to add till point
        int carry = 0;
        while(i > p1 || j > p2){
            int sum = carry;
            if(i > p1) sum += num1.charAt(i--) - '0';
            if(j > p2) sum += num2.charAt(j--) - '0';
            sb.append(sum % 10);
            carry = sum / 10;
        }
        //i j jump point and sb add point
        i--;
        j--;
        sb.append(".");

        //add 整数
        while(i >= 0 || j >= 0){
            int sum = carry;
            if(i >= 0) sum += num1.charAt(i--) - '0';
            if(j >= 0) sum += num2.charAt(j--) - '0';
            sb.append(sum % 10);
            carry = sum / 10;
        }        
        if(carry > 0) sb.append(carry);
        return sb.reverse().toString();
    }
}