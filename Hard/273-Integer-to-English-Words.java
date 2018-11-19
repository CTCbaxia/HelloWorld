/*
HARD
273. Integer to English Words

TIME: 
RESULT: 
*/
/*
Recursive for < 1000 + Iterative for >= 1000
小于 1000 的处理之后return，
大于 1000 的每三个为一个block，处理之后加上 Hundred 等的单位，然后 iterative 继续剩下的 block

Time: O(n)
Space: O(1)
*/
class Solution {
    public String numberToWords(int num) {
        String[] lessThanTwenty = {"", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine ", "Ten ", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};
        String[] tens = {"", "", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};
        String res = "";
        while(num > 0){
            if(num < 1000){
                int n1 = num / 100;
                int n2 = num % 100;
                if(n1 > 0) res += lessThanTwenty[n1] + "Hundred ";//百位
                if(n2 < 20){//十位 + 个位
                    res += lessThanTwenty[n2];
                }else{
                    res += tens[n2 / 10] + lessThanTwenty[n2 % 10];
                } 
                return res.substring(0, res.length() - 1);
            }else if(num < 1000000){
                res += numberToWords(num / 1000) + " Thousand ";
                num %= 1000;
            }else if(num < 1000000000){
                res += numberToWords(num / 1000000) + " Million ";
                num %= 1000000;
            }else{
                res += numberToWords(num / 1000000000) + " Billion ";
                num %= 1000000000;
            }            
        }
        return res.length() - 1 >= 0 ? res.substring(0, res.length() - 1) : "Zero";
    }

}