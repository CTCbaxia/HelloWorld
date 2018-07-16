/*
242. Valid Anagram
https://leetcode.com/problems/valid-anagram/description/

TIME: 0709
RESULT: 14%, 47ms
Notes: 
*/
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        
        List<Character> list_s = new ArrayList<Character>();
        List<Character> list_t = new ArrayList<Character>();
        for(int i = 0; i < s.length(); i++){
            list_s.add(s.charAt(i));
            list_t.add(t.charAt(i));
        }
        
        Collections.sort(list_s);
        Collections.sort(list_t);
        if(list_s.hashCode() == list_t.hashCode()){
            return true;
        }else{
            return false;
        }
        
    }
}

/*
read reference and improve

TIME: 0709
RESULT: 94%, 4ms

NOTE:
用数组的自定义方法显然比遍历 string 然后一个个添加到 List 中再比较要快很多

1. 把 String 变成 char 的数组： s.toCharArray();
2. 数组排序：  Arrays.sort(sArr);
3. 判断数组相等： Arrays.equals(sArr, tArr);
*/
public class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        
        return Arrays.equals(sArr, tArr);
    }
}