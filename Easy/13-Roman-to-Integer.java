/*
EASY
13. Roman to Integer

TIME: 
RESULT: 
NOTES:
*/
/*
Iteration
build unit<->val, if the next char is larger than the curent char, make -val

Time: O(n) , n = s.length
Space: O(1)
*/
class Solution {
    public int romanToInt(String s) {
        //build map
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        
        //compute
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(i + 1< s.length() && map.get(s.charAt(i + 1)) > map.get(c)) res -= map.get(c);
            else res += map.get(c);
        }
        return res;
    }
}






/*
找到对应数字
根据相对位置计算

Time: O(n)
Space: O(n)
*/
class Solution {
    public int romanToInt(String s) {
        int [] num = new int [s.length()];
        int sum = 0;
        for (int i=0; i<num.length; i++){//put every matching num into array
            switch (s.charAt(i)){
                case 'I' :num[i]=1;break;
                case 'V' :num[i]=5;break;
                case 'X' :num[i]=10;break;
                case 'L' :num[i]=50;break;
                case 'C' :num[i]=100;break;
                case 'D' :num[i]=500;break;
                case 'M' :num[i]=1000;break;
            }
        }
        for(int i=0; i< num.length-1;i++){//calculate sum
            if(num[i] >= num[i+1]){
                sum = sum + num[i];
            }else{//small before larger:IV, XL...
                sum = sum - num[i];
            }
        }
        sum = sum + num[num.length-1];//for the last word, no matter what it is, it must be +
        return sum;
    }
}