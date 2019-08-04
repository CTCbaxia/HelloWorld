/*
M
544. Output Contest Matches
*/
/*
Iterative
每一轮都把所有首位都配对，得到新的 string[]
然后对于新生成的数组，继续首尾配对

Time: O(n) = O(n) + O(n/2) + O(n/4) + ... + O(1)
Space: O(n)
*/
class Solution {
    public String findContestMatch(int n) {
        String[] data = new String[n];
        // initialize
        for(int i = 0; i < n; i++){
            data[i] = String.valueOf(i + 1);
        }
        
        while(n > 1){
            String[] newData = new String[data.length/2];
            int l = 0, r = data.length - 1, i = 0;
            while(l < r){
                newData[i++] = "(" + data[l] + "," + data[r] + ")";
                l++;
                r--;
            }
            data = newData;
            n /= 2;
        }
        return data[0];
    }

}
/*
Recursion
每一轮都把所有首位都配对，得到新的 string[]
然后对于新生成的数组，继续首尾配对

NOTE:!!!
recursion 就是每次都要等着后续的层次做完才能返回当前这层。return helper() 也是 recursion

Time: O(n)
Space: O(n)
*/
class Solution {
    public String findContestMatch(int n) {
        String[] data = new String[n];
        for(int i = 0; i < n; i++){
            data[i] = String.valueOf(i + 1);
        }
        return helper(data)[0];
    }
    private String[] helper(String[] data){
        if(data.length <= 1) return data;
        int n = data.length;
        String[] res = new String[n/2];
        int l = 0, r = n - 1, i = 0;
        while(l < r){
            res[i++] = "(" + data[l] + "," + data[r] + ")";
            l++;
            r--;
        }
        return helper(res);
    }
}
