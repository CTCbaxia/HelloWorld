/*
443. String Compression

*//*
Loop and Count

Time: O(n)
Space: O(1)
*/
class Solution {
    public int compress(char[] chars) {
        int index = 0;
        for(int i = 0; i < chars.length; i++){
            char c = chars[i];
            chars[index] = c;
            
            if(i + 1 < chars.length && chars[i + 1] == c){
                int count = 0;
                while(i < chars.length && chars[i] == c){
                    count++;
                    i++;
                }
                i--;//这里要自减，因为 for 循环自动会 + 1
                if(count >= 10){
                    chars[++index] = (char) (count/10 + '0');
                    chars[++index] = (char) (count%10 + '0');
                }
                else{
                    chars[++index] = (char) (count + '0');
                }
            }
            index++;//!!!外面还要再自增一遍 index，不然下一次会覆盖这一次的值
        }
        return index;//返回的正好是自增 1 之后的，也就是 index + 1 = 长度
    }
}