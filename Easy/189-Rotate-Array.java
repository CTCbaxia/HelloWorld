/*
EASY
189. Rotate Array

TIME: 
RESULT: 
NOTES:

*/
/*
3 Reverse: 翻转能够达到循环位移的效果
1. reverse all 0 ~ len - 1
2. reverse 0 ~ k - 1
3. reverse k ~ len - 1

Time: O(n)
Space: O(1)
*/
class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;//important to keep k < len
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
        return;
    }
    public void reverse(int[] nums, int start, int end){
        while(start < end){
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
        return;
    }
}


/*
Copy the array and use corresponding index (i + k) % len
这种题复制最直接

Time: O(n)
Space: O(n)
*/
class Solution {
    public void rotate(int[] nums, int k) {
        int[] tmp = nums.clone();
        for(int i = 0; i < nums.length; i++){
            nums[(i + k) % nums.length] = tmp[i]; 
        }
        return;
    }
}

/*
1) 先存下前面要移到后面的elments
2) 再把后面的 element 移到前面
3) 再把queue里面的 element 往后跟上
0 1 2 3 4

Time: O(n)
Space: O(n)
*/
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if(n == 0) return;
        k = k % n;

        //put num after k into queue
        Queue<Integer> q = new LinkedList<>();
        int len = nums.length - k;//k 之后能 hold 几个移动量
        int i = 0;
        while(i < len){
            q.offer(nums[i++]);
        }
        //move back to front
        int index = 0;//update val from 0 to nums.length - 1
        while(i < nums.length){
            nums[index++] = nums[(i++)%n];
        }
        //move elements in queue to the back
        while(index < nums.length){
            nums[index++] = q.poll();
        }
        return;
    }

}


/*
Move step by step
Time: O(kn)
Space: O(1)
*/
class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        while(k-- > 0){
            int tmp = nums[len - 1];
            for(int i = len - 1; i > 0; i--){
                nums[i] = nums[i - 1];
            }
            nums[0] = tmp;
        }
        return;
    }
}











//有问题
class Solution {
    public void rotate(int[] nums, int k) {
        int[] tmp = new int[k];
        int len = nums.length;
        int index = k;
        for(int i = len - 1; i >= len - k; i--){//tmp the last k elements
            tmp[--index] = nums[i];
        }
        
        for(int i = len - 1; i >= k; i--){//move elements from left to right
            nums[i] = nums[i - k];
        }
        
        for(int i = 0; i < k; i++){
            nums[i] = tmp[i];
        }
        return;
    }
}