/*

338. Counting Bits
https://leetcode.com/problems/counting-bits/description/

Result: 22%
*/

class Solution {
    public int[] countBits(int num) {
        int[] result = new int [num + 1];
        
        int[] head = {0,1,1,2,1,2,2,3};
        for(int i = 0; i <= num && i < 8; i++){
            result[i] = head[i]; 
        }
        if(num < 8){
            return result;
        }else{
            int n = 3;
            int[] base = {1,2,2,3};//n = 2 的结果
            int[] tmp = base;
            //n = 3 开始
            while( Math.pow(2,n) <= num ){

                tmp = subcount(tmp, n);
                
                int i = (int) Math.pow(2,n);
                int j = 0;
                while( i <= num && j < tmp.length){
                result[i] = tmp[j];
                i++;
                j++;
                }

             n++;
            }
        }
        return result;
    }
    //下一个范围等于（上一个范围） + （上一个范围的数组 + 1）
    private int[] subcount(int[] sub, int n){
        //当 2^n ~ 2^n+1 范围内，每 4 个的加数
        int sublength = (int) Math.pow(2,n - 3) * 4;
        int length = sublength * 2;
        int tmp[] = new int [length];

        for (int i = 0; i < length; i++){
            if(i < sublength){
                tmp[i] = sub[i]; 
            }else{
                tmp[i] = sub[i - sublength] + 1;
            }

        }
        return tmp;
    }
}