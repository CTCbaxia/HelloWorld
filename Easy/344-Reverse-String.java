/*
EASY
344. Reverse String
https://leetcode.com/problems/reverse-string/description/

TIME: 0929 - 2min
RESULT: 100% - 1ms
NOTES: 

*/
//char[] swap
class Solution {
    public String reverseString(String s) {
        char[] c = s.toCharArray();
        for(int i = 0; i < c.length/2; i++){
            char tmp = c[i];
            c[i] = c[c.length - 1 - i];
            c[c.length - 1 - i] = tmp;
        }
        return String.valueOf(c);
    }
}


class Solution {
    public String reverseString(String s) {
        char[] c = s.toCharArray();
        int i = 0;
        int j = c.length - 1;
        while(i < j){
            char tmp = c[i];
            c[i] = c[j];
            c[j] = tmp;
            i++;
            j--;
        }
        return new String(c);
    }
}

//StringBuilder to build another

class Solution {
    public String reverseString(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
}


class Solution {
    public String reverseString(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = s.length() - 1; i >= 0; i--){
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}