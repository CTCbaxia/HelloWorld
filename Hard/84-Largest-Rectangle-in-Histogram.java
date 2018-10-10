/*
HARD
84. Largest Rectangle in Histogram


TIME: 1010 - 30min
RESULT: 81% - 15ms
NOTES:
思路就是loop once
如果一个比一个大，就将[height, index]放入stack
如果遍历到的小于 stack 的值，就 pop 并计算 pop 出来的 size，知道遇到等于或大于 stack 的值
如果等于，什么都不做 continue
如果小于，那么面积应该等于现有最后一个pop 出来的 index，现在的height，作为 int[] 输入
*** 最后注意stack 排空

*/
class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<int[]> stack = new Stack<int[]>();
        int size = 0;
        for(int i = 0; i < heights.length; i++){
            if(stack.isEmpty()){
                stack.push(new int[]{heights[i], i});
            }else{
                if(stack.peek()[0] < heights[i]) stack.push(new int[]{heights[i], i});
                else if(stack.peek()[0] > heights[i]){
                    //pop the rec until we find the same or lower rec
                    int[] rec = new int[2];
                    while(!stack.isEmpty() && stack.peek()[0] > heights[i]){
                        rec = stack.pop();
                        size = Math.max(rec[0] * (i - rec[1]), size);
                    }
                    if(!stack.isEmpty() && stack.peek()[0] == heights[i]) continue;
                    stack.push(new int[]{heights[i], rec[1]});                        
                }
            }
        }
        while(!stack.isEmpty()){
            int[] rec = stack.pop();
            size = Math.max(size, rec[0] * (heights.length - rec[1]));
        } 
        return size;
    }
}