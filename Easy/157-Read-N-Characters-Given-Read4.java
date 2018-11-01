/*
EASY
157. Read N Characters Given Read4

RESULT: 98% - 3ms
NOTES: 
*/
/*
Time: O(n)
Space: O(1)
*/
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); 
read4(buffer) return the number of bytes read and save the thing into buffer
We need to move the buffer we read to the buf input     
*/

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] buffer = new char[4];
        boolean end = false;
        int haveRead = 0;
        while(!end && haveRead < n){//没读完 && 读的数量少于 n 才会继续
            int num = read4(buffer);//put into buffer
            if(num < 4) end = true;
            
            int len = Math.min(num, n - haveRead);
            for(int i = 0; i < len; i++){
                buf[haveRead + i] = buffer[i];
            }
            haveRead += len;
        }
        return haveRead;
    }
}
