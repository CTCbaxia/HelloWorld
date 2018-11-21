/*
HARD
65. Valid Number

TIME: 
RESULT: 
*/
/*
four flags
1. should be number
2. should be number after e, should not be point after e
3. should have at most one point
4. should have at most one e
5. +- can only be at 0 or after e

Time: O(n)
Space: O(1)
*/
class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        boolean numSeen = false;
        boolean numAfterE = false;
        boolean eSeen = false;
        boolean pointSeen = false;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                numSeen = true;
                numAfterE = true;
            }else if(c == '.'){
                if(eSeen || pointSeen) return false;
                pointSeen = true;
            }else if(c == 'e'){
                if(eSeen || !numSeen) return false;
                eSeen = true;
                numAfterE = false;//should be number after e
            }else if(c == '+' || c == '-'){
                if(i != 0 && s.charAt(i - 1) != 'e') return false;//+- can only be at 0 or after e
            }else{
                return false;
            }
            
        }
        return numSeen && numAfterE;
    }

}







/*
before e: could be +/-小数
after e: should be +/-整数
*/
class Solution {
    public boolean isNumber(String s) {
        String str= s.trim();
        return check(str, 1, 1);
    }
    private boolean check(String s, int point, int exp){
        if(s.length() == 0) return false;//should have something
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '-' || c == '+'){//can have sign at 0
                if(i != 0 || i == s.length() - 1) return false;//sign should be first one but not last
            }else if(c == '.'){
                if(point == 0 || s.length() == 1) return false;//exceed point limit or only a "."
                if(i > 0 && !Character.isDigit(s.charAt(i - 1))) return false;
                point--;
            }else if(c == 'e'){
                /*
                1. should not have e in the second half(小数部分)
                2. should have num before and after e
                3. should not have sign before e
                */
                if(exp == 0 || i == 0 || i == s.length() - 1) return false;
                if(!Character.isDigit(s.charAt(i - 1))) return false; //".e1","+e8" false
                return check(s.substring(i + 1), 0, 0);//check num after e
            }else if(!Character.isDigit(c)){
                
                return false;
            } 
        }
        return true;
    }
}
//".e1" false
//"0.e3" true