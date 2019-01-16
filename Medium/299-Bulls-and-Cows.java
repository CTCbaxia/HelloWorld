/*
MEDIUM
299. Bulls and Cows

*/
/*
A: 完全相等的数字（包括位置）
B: 对于每个数字[0-9]，计算 secret 以及 guess 中未被匹配的数量，n = min() 就是他俩都有 n 个数字 num，且这 n 个数字 num 均没有对位

Time: O(n)
Space: O(n)
*/
class Solution {
    public String getHint(String secret, String guess) {
        int[] s = new int[10];
        int[] g = new int[10];
        int a = 0, b = 0;
        for(int i = 0; i < secret.length(); i++){
            int digitS = secret.charAt(i) - '0';
            int digitG = guess.charAt(i) - '0';
            
            if(digitS == digitG) a++;
            else{
                s[digitS]++;
                g[digitG]++;                
            }
        }
        
        for(int i = 0; i < 10; i++){
                b += Math.min(g[i], s[i]);
        }
        return a + "A" + b + "B";
    }
}


class Solution {
    public String getHint(String secret, String guess) {
        int[] s = new int[10];
        int[] g = new int[10];
        int[] match = new int[10];
        int a = 0, b = 0;
        for(int i = 0; i < secret.length(); i++){
            int digitS = secret.charAt(i) - '0';
            int digitG = guess.charAt(i) - '0';
            
            if(digitS == digitG){
                a++;
                match[digitS]++;
            } 
            s[digitS]++;
            g[digitG]++;
        }
        
        for(int i = 0; i < 10; i++){
            if(g[i] > match[i] && s[i] > match[i])
                b += Math.min(g[i] - match[i], s[i] - match[i]);
        }
        return a + "A" + b + "B";
    }
}