/*
EASY
345. Reverse Vowels of a String
https://leetcode.com/problems/reverse-vowels-of-a-string/description/

TIME: 0724 - 20min
RESULT: 70% - 5ms
METHODS:
1. two pointers - half loop
2. Stack - loop

*/
/*
SOLUTION 0:
TIME: 0724 - 20min
RESULT: 70% - 5ms
*/

class Solution {
    public String reverseVowels(String s) {
        List<Character> vowelall = Arrays.asList('a','e','i','o','u','A','E','I','O','U');
        char[] chars = s.toCharArray();
        int ascpointer = 0;
        int descpointer = s.length() - 1;
        while(ascpointer < descpointer){
            while(ascpointer < descpointer && !vowelall.contains(chars[ascpointer])) ascpointer++;
            while(ascpointer < descpointer && !vowelall.contains(chars[descpointer])) descpointer--;
            char tmp = chars[ascpointer];
            chars[ascpointer] = chars[descpointer];
            chars[descpointer] = tmp;
            descpointer--;
            ascpointer++;
        }

        return String.valueOf(chars);
    }
}
/*
SOLUTION 1:
TIME: 0724 - 20min
RESULT: 9% - 18ms
*/
class Solution {
    public String reverseVowels(String s) {
        List<Character> vowelall = Arrays.asList('a','e','i','o','u','A','E','I','O','U');
        StringBuilder sb = new StringBuilder();
        int ascpointer = 0;
        int descpointer = s.length() - 1;
        while(ascpointer < s.length()){
            if(!vowelall.contains(s.charAt(ascpointer))) sb.append(s.charAt(ascpointer));
            else{
                while(!vowelall.contains(s.charAt(descpointer))){
                    descpointer--;
                }
                sb.append(s.charAt(descpointer));
                descpointer--;
            }
            ascpointer++;
        }

        return sb.length() == 0 ? "" : sb.toString();
    }
}


/*
SOLUTION 2:
TIME: 0724 - 10min
RESULT: 33% - 10ms
*/

class Solution {
    public String reverseVowels(String s) {
        Stack<Character> vowel = new Stack<Character>();
        List<Character> vowelall = Arrays.asList('a','e','i','o','u','A','E','I','O','U');
        for(int i = 0; i < s.length(); i++){
            if(vowelall.contains(s.charAt(i))) vowel.push(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(vowelall.contains(s.charAt(i))) sb.append(vowel.pop());
            else sb.append(s.charAt(i));
        }
        return sb.length() == 0 ? "" : sb.toString();
    }
}
//TEST CASE: "aA"