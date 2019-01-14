/*
HARD
158. Read N Characters Given Read4 II - Call multiple times

TIME: 
RESULT: 
*/
/*
重点是要从上次读的buff里面继续读取，所以需要保留上次的 preBuf，以及上次读到 preBuf 里面的哪里了（preIndex）

Time: O(n)
Space: O(4)
*/

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

    char[] preBuf = new char[4];//for buf for read4
    int preIndex = 0;//之前读到哪里了
    int preSize = 0;//prebuf 里面元素的总size
    public int read(char[] buf, int n) {//put everyread into the buf, return #chars in buf, #chars <= n
        int count = 0;
        while(count < n){
            if(preIndex < preSize){
                buf[count++] = preBuf[preIndex++];
            }else{//have read all from prebuf, but still have not fill up the buf, need to read another buf
                preSize = read4(preBuf);
                preIndex = 0;
                if(preSize == 0) break;//cannot read more
            }
        }
        return count;//how many chars are in buf
    }
} 
 




 
/*
重点是要从上次读的buff里面继续读取，所以需要保留上次的 preBuf，以及上次读到 preBuf 里面的哪里了（preIndex）

Time: O(n)
Space: O(4)
*/
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    int preIndex = 0;//read4 里面写入 buf 的index（写到哪里了）
    int preSize = 0;//read4 读取出来的size
    char[] preBuf = new char[4];
    
    
    public int read(char[] buf, int n) {
        int counter = 0;
        while(counter < n){
            if(preIndex < preSize){//当counter < n，一个一个读取 preBuf里面的内容
                buf[counter++] = preBuf[preIndex++];
            }else{//have read all from prebuf, but still have not fill up the buf
                preSize = read4(preBuf);//update preBuf
                preIndex = 0;
                if(preSize == 0) break;
            }
        }
        return counter;
    }
}