/*
HARD
85. Maximal Rectangle


TIME: 1010 - 30min
RESULT: 81% - 15ms
NOTES:
利用 84 题
每次算一层的 findLargest

*/
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] height = new int[matrix[0].length];
        int result = 0;
        for(char[] row : matrix){
            for(int i = 0; i < row.length; i++){
                if(row[i] == '1') height[i] += 1;
                else height[i] = 0;
            }
            result = Math.max(result, findLargest(height));
        }
        return result;
        
    }

    private int findLargest(int[] height){
        int len = height.length;
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len) ? 0 : height[i];
            if(stack.isEmpty() || h > height[stack.peek()]){
                stack.push(i);
            }else{
                int index = stack.pop();
                max = Math.max(max, height[index] * ((stack.isEmpty() ? i : i - 1 - stack.peek())));
                i--;
            }
            
        }
        return max;
    }
}