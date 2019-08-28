/*
HARD
273. Integer to English Words

TIME: 
RESULT: 
*/
/*
Recursion

Time: O(1) - O(9)?
Space: O(1) - stack O(3)?

*/
class Solution {
    public String numberToWords(int num) {
        if(num == 0) return "Zero";//单独处理 0
        return getNum(num);
    }
    private String getNum(int num){
        String[] belowTwenty = {"","One","Two","Three","Four","Five","Six","Seven", "Eight","Nine","Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
        String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String[] unit = {"", "Hundred", "Thousand", "Million", "Billion"};
        
        String res;//不能直接返回，要去除尾部空格
        if(num < 20) res = belowTwenty[num];
        else if(num < 100) res = tens[num / 10] + " " + belowTwenty[num % 10];
        else if(num < 1000) res = getNum(num / 100) + " " + unit[1] + " " +  getNum(num % 100);
        else if(num < 1000000) res = getNum(num / 1000) + " " +  unit[2] + " " +  getNum(num % 1000);
        else if(num < 1000000000) res = getNum(num / 1000000) + " " +  unit[3] + " " +  getNum(num % 1000000);
        else res = getNum(num / 1000000000) + " " +  unit[4] + " " +  getNum(num % 1000000000);
        return res.trim();// 去掉尾部空格， getNum 应该得到一个没有前后空格的结果
    }
}



/*
Recursive: 注意 0 要在主函数处理
recursive 每一次按照单位量处理
注意 getNum 要得到前后没有空格的数字

Time: O(n)
Space: O(1)
*/
class Solution {
    public String numberToWords(int num) {
        if(num == 0) return "Zero";//单独处理
        else return getNum(num);//9800 最后会有一个 " " 要去掉
    }
    
    private String getNum(int num){//得到前后没有空格的数字
        String[] lessThanTwenty = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen","Eighteen", "Nineteen"};
        String[] tens = new String[]{"", "","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
        String[] unit = new String[]{"", "Hundred","Thousand","Million","Billion"};

        String res = "";
        if(num < 100){
            if(num < 20) res = lessThanTwenty[num];
            else res = tens[num / 10] + " " + lessThanTwenty[num % 10];
        }else if(num < 1000){
            res = getNum(num / 100) + " " + unit[1] + " " + getNum(num % 100);
        }else if(num < 1000000){
            res = getNum(num / 1000) + " " + unit[2] + " " + getNum(num % 1000);
        }else if(num < 1000000000){
            res = getNum(num / 1000000) + " " + unit[3] + " " + getNum(num % 1000000);
        }else{
            res = getNum(num / 1000000000) + " " + unit[4] + " " + getNum(num % 1000000000);
        }
        return res.trim();//50868
    }                                
}



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


