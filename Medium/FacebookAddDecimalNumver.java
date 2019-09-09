/*
Facebook - Add Decimal Numbers


Time: O(m + n)
Space: O(max(m,n)) for stringbuilder
*/
public class FacebookAddDecimalNumver {
    public static void main(String[] args) {
        String str1 = ".321121224";
        String str2 = "8.75112123434";
        System.out.println(addNum(str1, str2) +" == "+(0.321121224 + 8.75112123434));
    }
    public static String addNum(String str1, String str2){
        int indexOfDot1 = str1.indexOf(".");
        int indexOfDot2 = str2.indexOf(".");
        int count1 = indexOfDot1 == -1 ? 0 : str1.length() - indexOfDot1 - 1;//可以直接 str1.length() - indexOfDot1 - 1
        int count2 = indexOfDot2 == -1 ? 0 : str2.length() - indexOfDot2 - 1;
        StringBuilder sb = new StringBuilder();
        int i1 = str1.length() - 1, i2 = str2.length() - 1; 
        //decimal
        if(count1 < count2){
            int diff = count2 - count1;
            while(diff-- > 0){
                sb.append(str2.charAt(i2--));
            }
        }else if(count1 > count2){
            int diff = count1 - count2;
            while(diff-- > 0){
                sb.append(str1.charAt(i1--));
            }
        }
        int carry = 0;
        if(indexOfDot1 != -1 || indexOfDot2 != -1){
            while(i1 > indexOfDot1 && i2 > indexOfDot2){
                int num = carry;
                num += str1.charAt(i1--) - '0';
                num += str2.charAt(i2--) - '0';
                sb.append(num % 10);
                carry = num / 10;
            }
            sb.append('.');
            if(indexOfDot1 != -1) i1--;//skip decimal point
            if(indexOfDot2 != -1) i2--;
        }
        
        //int part
        while(i1 >= 0 || i2 >= 0){
            int num = carry;
            num += i1 >= 0 ? str1.charAt(i1--) - '0' : 0;
            num += i2 >= 0 ? str2.charAt(i2--) - '0' : 0;
            sb.append(num % 10);
            carry = num / 10;
        }
        if(carry > 0) sb.append(carry);
        return sb.reverse().toString();
        
    }
}