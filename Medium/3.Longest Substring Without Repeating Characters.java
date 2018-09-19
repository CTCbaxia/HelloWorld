/*
Solution 0: 这个方法应该没问题，就是时间太长无法执行

*/


class Solution {
    public int lengthOfLongestSubstring(String s) {
        int length = 0;
        
        if(s.length()==0){
            return length;
        }else{
            length = 1;
        }
        int length_next = 1;
        String substring = "";
        for(int i=0; i<s.length();i++){
            substring = "" + s.charAt(i);
            for(int j=i+1; j<s.length();j++){
                int index = substring.indexOf(s.charAt(j));
                if(index != -1){
                    break;
                }else{
                    substring = substring + s.charAt(j);
                    length_next++;
                }
            }
            if(length < length_next){
                length = length_next;  
            }
            length_next = 1;

        }
        return length;
    }
}

/*
Solution 1: 43%

参考discuss里面的讨论做的。
学会了用hashset。
还要加强。

*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0){
            return 0;
        }
        
        HashSet<Character> substring = new HashSet<Character> ();
        int maxlength = 0;
        int i = 0;
        int j = 0;
        while(i < s.length()){
            if(!substring.contains(s.charAt(i))){
                substring.add(s.charAt(i++));
                maxlength = Math.max(maxlength,substring.size());

            }else{
                substring.remove(s.charAt(j++));
            }
        }
        return maxlength;
    }
}