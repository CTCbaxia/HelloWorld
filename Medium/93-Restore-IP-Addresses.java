/*
MEDIUM
93. Restore IP Addresses
https://leetcode.com/problems/restore-ip-addresses/description/
TIME: 0719 - 2h
RESULT: 93% - 2ms
NOTES:
    - 每个IP段的范围是0-255
    - 要用整个序列，而不是它的子集
1. 代码里不要有 System.out.println() 这些东西，很慢
2. 遇到字符串的子序列或配准问题首先考虑动态规划DP; 遇到需要求出所有可能情况首先考虑用递归
3. 在递归中，尽量只用引用变量存结果，不要传递，会乱

METHOD:
1. RECURSIVE
2. 选择每个 . 插入的位置
NOTE:

*/

/*
SOLUTION 0: 
TIME: 0719 - 10min
RESULT: 93% - 2ms
*/
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        nextLevel(0, s, result, "", 0);
        return result;
    }
    private void nextLevel(int start, String s, List<String> result, String pre, int level){
        if(start + (4 - level)*3 < s.length() || start + 4 - level > s.length()) return;//省去很多不必要的计算
        for(int i = 0; i < 3; i++){
            if(start + i + 1 > s.length()) return;
            String levelres = s.substring(start, start + i + 1);
            if(levelres.length() > 1 && levelres.charAt(0) == '0') return;
            if(Integer.parseInt(levelres) <= 255){
                if(level == 3 && start + i + 1 == s.length()){
                        result.add(pre + levelres);
                        continue;
                }
                nextLevel(start + i + 1, s, result, pre + levelres + ".", level + 1);                
            }

        }
        return;
    }
}    
/*
TIME: 0719 - 1h
RESULT: 11% - 7ms
*/
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        if(s.length() > 12) return result;

        nextLevel(0, s, result, "", 0);
        return result;

    }
    private void nextLevel(int start, String s, List<String> result, String pre, int level){
        for(int i = 0; i < 3; i++){
            if(start + i + 1 > s.length()) return;
            
            String levelres = s.substring(start, start + i + 1);
            if(levelres.length() > 1 && levelres.charAt(0) == '0') return;
            if(Integer.parseInt(levelres) <= 255){
                if(level == 3 && start + i + 1 == s.length()){
                        result.add(pre + levelres);
                        continue;
                }
                nextLevel(start + i + 1, s, result, pre + levelres + ".", level + 1);                
            }
        }
        return;
    }
}

/*
SOLUTION REFERENCE
TIME: 0719 - 20min
RESULT: 93% - 2ms

巧妙之处在于所有的数字都一定会在结果中，所以就是看三个点往哪里插的问题
*/
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        int n = s.length();
        if(n > 12 || n < 4) return result;
        
        for(int i = 0; i < 3 && i < n - 3; i++){
            for(int j = i + 1; j < i + 4 && j < n - 2; j++){
                for(int k = j + 1; k < j + 4 && k < n - 1; k++){
                    if(n - (k + 1) > 3) continue;
                    String s1 = s.substring(0,i + 1), s2 = s.substring(i + 1,j + 1), s3 = s.substring(j + 1,k + 1), s4 = s.substring(k + 1,n);
                    if(isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4) result.add(s1 + "." + s2 + "." + s3 +  "." + s4);
                }
            }
        }
        return result;
    }
    private boolean isValid(String s){
        if(s.length() > 3 || Integer.parseInt(s) > 255 || (s.length() > 1 && s.charAt(0) == '0')) return false;
        else return true;
    }
}
