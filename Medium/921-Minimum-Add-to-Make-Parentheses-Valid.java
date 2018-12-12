/*
MEDIUM
921. Minimum Add to Make Parentheses Valid

TIME: 
RESULT: 
NOTES:

*/
/*
Counter: One pass
分别记录左边和右边需要的匹配量，最后相加

Time: O(n)
Space: O(1)
*/
class Solution {
    public int minAddToMakeValid(String S){
        int left = 0;//多余的 (
        int right = 0;//多余的 )
        for(char c : S.toCharArray()){
            if(c == '(') left++;
            else{
                if(left > 0) left--;
                else right++;//有多余的 )，right++ 相当于给他找了个匹配
            }
        }
        return left + right;
    }
}



/*
301 一个思路
先 check 左边需要的，再 check 右边需要的

Time: O(n)
Space: O(1)
*/
class Solution {
    public int minAddToMakeValid(String S){
        int left = makeValid(S, '(', ')');
        int right = makeValid(new StringBuilder(S).reverse().toString(), ')', '(');
        return left + right;
    }
    private int makeValid(String s, char lc, char rc){
        int res = 0;
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == lc) count++;
            else{
                count--;
                if(count < 0){//当记录了一个res, 相当于已经补上了
                    res++;
                    count = 0;
                } 
            }
        }
        return res;
    }
}