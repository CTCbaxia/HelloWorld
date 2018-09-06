/*
MEDIUM
179. Largest Number
https://leetcode.com/submissions/detail/174263735/

TIME: 
RESULT: 
NOTES:
注意 comparator 的使用，和这题需要反向排序

THOUGHTS:
最开始的思路是：如果同位，排大小；如果不同位，多一位的下一位小于自己，就降级，大于自己，就升级；
---这个太复杂
重点是如何让前后两个数便于比较。最好的比较大小的办法就是让两个长度相等的数字进行比较。
而能够使 n1, n2 形成长度相等的方法就是转换成 String，再 s1+s2。
而String.compareTo(another string) 这个方法很好的实现了每个字符串的比较（其实跟 int 一样）

*/

//comparator 写法 1
class Solution {
    public String largestNumber(int[] nums) {
        if(nums.length == 0 || nums == null) return "";
        String[] numstr = new String[nums.length];
        
        for(int i = 0; i < nums.length; i++){
            numstr[i] = String.valueOf(nums[i]);
        }
        
        Arrays.sort(numstr, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));
        
        if(numstr[0].charAt(0) == '0') return "0";
        StringBuilder sb = new StringBuilder();
        for(String s : numstr) sb.append(s);
        return sb.toString();
    }
}
//comparator 写法 2
class Solution {
    public String largestNumber(int[] nums) {
        if(nums.length == 0 || nums == null) return "";
        String[] numstr = new String[nums.length];
        for(int i = 0; i < nums.length; i++){
            numstr[i] = String.valueOf(nums[i]);
        }
        
        Arrays.sort(numstr, new Comparator<String>(){
            public int compare(String s1, String s2){
                return (s2 + s1).compareTo(s1 + s2);
            }
        });
        
        if(numstr[0].charAt(0) == '0') return "0";
        StringBuilder sb = new StringBuilder();
        for(String s : numstr) sb.append(s);
        return sb.toString();
    }
}

//comparator 写法 3
class Solution {
    public String largestNumber(int[] nums) {
        if(nums.length == 0 || nums == null) return "";
        String[] numstr = new String[nums.length];
        
        for(int i = 0; i < nums.length; i++){
            numstr[i] = String.valueOf(nums[i]);
        }
        
        Comparator<String> com = new Comparator<String>(){
            public int compare(String s1, String s2){
                String str1 = s1 + s2;
                String str2 = s2 + s1;
                return str2.compareTo(str1);
            }
        };
        Arrays.sort(numstr, com);
        
        if(numstr[0].charAt(0) == '0') return "0";
        StringBuilder sb = new StringBuilder();
        for(String s : numstr) sb.append(s);
        return sb.toString();
    }
}