/*
EASY
157. Read N Characters Given Read4

RESULT: 98% - 3ms
NOTES: 
*/
/*
注意相对长度：
index, size to control the read4
counter to count for letters copied to buf

int read4(char[] buf) : 将读到的（最多）四个letter放进 buf 里面，返回读到了多少个词
int read(char[] buf, int n): 希望读 n 个词，放进 buf 里面，最后返回 buf 里面到底放了多少个词

Time: O(n)
Space: O(1)
*/
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] buffer = new char[4];
        int index = 0;//for buffer
        int size = 0;//read size after read4
        int counter = 0;
        
        while(counter < n){//还没读到要求的n
            if(index < size){//buffer 还没读完
                buf[counter++] = buffer[index++];
            }else{//buffer 读完了
                size = read4(buffer);//update buffer
                index = 0;
                if(size == 0) break;//nothing more to read
            }
        }
        return counter;//how many we have write to buf
    }
}





/*
注意相对长度：
int read4(char[] buf) : 将读到的（最多）四个letter放进 buf 里面，返回读到了多少个词
int read(char[] buf, int n): 希望读 n 个词，放进 buf 里面，最后返回 buf 里面到底放了多少个词

Time: O(n)
Space: O(1)
*/
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] buffer = new char[4];
        boolean end = false;//标记是否读完
        int haveRead = 0;
        while(!end && haveRead < n){//没读完 && 读的数量少于 n 才会继续
            int num = read4(buffer);//put into buffer
            if(num < 4) end = true;
            
            int len = Math.min(num, n - haveRead);//读取的 num, 剩余还要写进 buf 的 n - hasRead
            for(int i = 0; i < len; i++){
                buf[haveRead + i] = buffer[i];
            }
            haveRead += len;
        }
        return haveRead;
    }
}