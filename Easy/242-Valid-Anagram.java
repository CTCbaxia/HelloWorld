/*
242. Valid Anagram

TIME: 0709
RESULT: 14%, 47ms
Notes: 
*/
/*
计算频度（int[26] count），一加一减
只有所有字母count == 0 的时候才return true

Time: O(n)
Space: O(26)
*/
class Solution {
    public boolean isAnagram(String s, String t){
        int[] count = new int[26];
        for(int i = 0; i < s.length(); i++) count[s.charAt(i) - 'a']++;
        for(int i = 0; i < t.length(); i++) count[t.charAt(i) - 'a']--;
        for(int i = 0; i < count.length; i++){
            if(count[i] != 0) return false;
        }
        return true;
    }
}


/*
排序 
Time: O(nlogn) 
Space: O(n)
*/
public boolean isAnagram(String s, String t){
    if(s.length() != t.length()) return false;
    char[] sch = s.toCharArray();
    char[] tch = t.toCharArray();
    
    Arrays.sort(sch);
    Arrays.sort(tch);
    for(int i = 0; i < s.length(); i++){
        if(sch[i] != tch[i]) return false;
    }
    return true;
} 


/*
Map 频度
Time: O(n)
Space: O(26)
*/
public boolean isAnagram(String s, String t){
    if(s.length() != t.length()) return false;
    Map<Character, Integer> map = new HashMap<>();
    for(int i = 0; i < s.length(); i++){
        char c = s.charAt(i);
        map.put(c, map.getOrDefault(c, 0) + 1);
    }
    int key = map.size();
    for(int i = 0; i < t.length(); i++){
        char c = t.charAt(i);
        if(!map.containsKey(c)) return false;
        map.put(c, map.get(c) - 1);
        if(map.get(c) == 0) {
            key--;
            map.remove(c);
        }
    }
    return key == 0;
}





























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