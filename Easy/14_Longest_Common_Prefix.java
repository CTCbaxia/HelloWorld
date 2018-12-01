/*
Solution 1: 非常直观的对比法
**********TBD
Notes:
1.对于for循环，可以有int i=1这样的起步，因为对于空数组来说，一开始的初始化之后，判断终止条件就会发现for循环无法执行，所以不会运行for循环。
2.对于字符串内字符的增加和联合，直接把两个字符串相加就好
*/
/*
compare every letter
use first string as standard

Time: O(n)
Space: O(1)
*/
class Solution {
    public String longestCommonPrefix(String[] strs){
        if(strs.length == 0) return "";

        String res = "";
        for(int i = 0; i < strs[0].length(); i++){
            char c = strs[0].charAt(i);
            for(int j = 1; j < strs.length; j++){
                if(i >= strs[j].length()) return res;
                if(c != strs[j].charAt(i)) return res;
            }
            res += c;
        }
        return res;
    }
}







class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        int len = strs[0].length();
        for(int i = 0; i < len; i++){
            char c = strs[0].charAt(i);
            for(int j = 1; j < strs.length; j++){
                if(i >= strs[j].length()) return strs[j];
                if(strs[j].charAt(i) != c) return strs[j].substring(0, i);
            }
        }
        return strs[0];
    }
}





class Solution {
    public String longestCommonPrefix(String[] strs) {
        String prefix = "";
        String tmp = "";
        int index = 0;
        
        if(strs.length==0){
            return prefix;
        }else{
         tmp = strs[0];
        }
        
        for(int i=1; i<strs.length; i++){
            if (strs[i].length() <= tmp.length()){
                tmp = strs[i];
                index = i;
            }
        }
        for(int i=0; i<strs[index].length();i++){
            for(int j=0; j<strs.length; j++){
                if(strs[j].charAt(i) != strs[index].charAt(i)){
                    return prefix;
                }
                if(j==strs.length-1){
                    prefix=prefix+strs[j].charAt(i);
                }
            }
            
        }
        return prefix;
    }
}

/*
失败runtime error
没有考虑到strs是一个空数组
String [] strs = new String [0];
空数组可以求长度，但是不能引用数组元素(strs[0])
所以应该在引用数组元素之前就判断是否为空数组
*/


class Solution {
    public String longestCommonPrefix(String[] strs) {
        String tmp = strs[0];
        int index = 0;
        String prefix = "";
        for(int i=1; i<strs.length; i++){
            if (strs[i].length() <= tmp.length()){
                tmp = strs[i];
                index = i;
            }
        }
        for(int i=0; i<strs[index].length();i++){
            for(int j=0; j<strs.length; j++){
                if(strs[j].charAt(i) != strs[index].charAt(i)){
                    return prefix;
                }
                if(j==strs.length-1){
                    prefix=prefix+strs[j].charAt(i);
                }
            }
            
        }
        return prefix;
    }
}