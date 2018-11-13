/*
MEDIUM
681. Next Closest Time

TIME: 
RESULT: 
NOTES:

*/
/*
Sort + Check(Bound)
get four digits and sort
From right to left:
Assign new value to the digit

if new value is larger, return
if new value is smaller, continue

Time: O(1)
Space: O(1)
*/
class Solution {
    public String nextClosestTime(String time) {
        char[] num = new char[4];
        char[] digit = time.toCharArray();
        int index = 0;
        //get every digit into num
        for(char c : digit){
            if(c == ':') continue;
            num[index++] = c;
        }
        Arrays.sort(num);
        
        //HH:M_
        digit[4] = findNext(num, '9', digit[4]);
        if(digit[4] > time.charAt(4)){
            return String.valueOf(digit);
        }
        //HH:_M
        digit[3] = findNext(num, '5', digit[3]);
        if(digit[3] > time.charAt(3)){
            return String.valueOf(digit);
        }  
        //H_:MM (2_ or 1_ 范围不一样)
        digit[1] = digit[0] == '2' ? findNext(num, '4', digit[1]) : findNext(num, '9', digit[1]);
        if(digit[1] > time.charAt(1)){
            return String.valueOf(digit);
        } 
        //_H:MM
        digit[0] = findNext(num, '2', digit[0]);
        return String.valueOf(digit);
        
        
    }
    //find the next value or change to the smallest value
    private char findNext(char[] num, char limit, char timedigit){
        int index = 0;
        while(index < num.length && num[index] <= timedigit){
            index++;
        }
        if(index < num.length && num[index] <= limit) return num[index];
        else return num[0];
    }
}