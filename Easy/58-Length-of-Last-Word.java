/*
EASY
58. Length of Last Word
https://leetcode.com/problems/length-of-last-word/description/

TIME: 0824 - 10min
RESULT: 99% - 3ms
NOTES:
1. split 中间的空格会在，最后的会直接没有？
*/
class Solution {
    public int lengthOfLastWord(String s) {
        int result = 0;
        boolean start = false;
        for(int i = s.length() - 1; i >= 0; i--){
            if(s.charAt(i) != ' '){
                start = true;
                result++;
            }
            else if(s.charAt(i) == ' ' && start) break;
        }
        return result;
    }
}

/*


*/
class Solution {
    public int lengthOfLastWord(String s) {
        String[] words = s.split(" ");
        if(words.length == 0){
        return 0;
        }

        return words[words.length - 1].length();
        
    }
}

//test case：
for(int i = 0; i < words.length; i++){
    System.out.println("i:" + i + ":" + words[i]);
}

input: "Hello      World    h       h   ggg      "
output:
i:0:Hello
i:1:
i:2:
i:3:
i:4:
i:5:
i:6:World
i:7:
i:8:
i:9:
i:10:h
i:11:
i:12:
i:13:
i:14:
i:15:
i:16:
i:17:h
i:18:
i:19:
i:20:ggg