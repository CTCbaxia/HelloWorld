/*
HARD
42. Trapping Rain Water
*/
/*
Brute Force:
for each point, search leftmax and rightmax, the water can be Math.min(leftmax, rightmax)

Time: O(n^2)
Space: O(1)

Dynamic Programming:
just save leftmax and rightmax for each point
for every point: res += Math.min(leftMax[i], rightMax[i]) - height[i];

Time: O(n)
Space: O(n)
*/
class Solution {
    public int trap(int[] height) {
        int res = 0;
        int[] leftMax = new int[height.length];//leftMax[i] before i, what is the heighest 
        int[] rightMax = new int[height.length];
        int max = 0;
        for(int i = 0; i < height.length; i++){
            max = Math.max(max, height[i]);
            leftMax[i] = max;
        }
        max = 0;
        for(int i = height.length - 1; i >= 0; i--){
            max = Math.max(max, height[i]);
            rightMax[i] = max;
        }    
        for(int i = 0; i < height.length; i++){
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }        
        return res;
    }
}











/*
HARD
42. Trapping Rain Water
https://leetcode.com/problems/trapping-rain-water/description/

TIME: 0711 - 1h
RESULT: 0% - 212ms
TODO: TO BE IMPROVED

思路：
1. 从左往右第一个非 0 元素为起始点
2. 0 层：在 0 的两边最近的非 0 值的最小高度为能存水的 height，连续的 0 值为 width
   1 层：在 1 的两边最近的非 1 值的最小高度为能存水的 height，连续的 1 值为 width
   ...
*/
class Solution {
    public int trap(int[] height) {
        int result = 0;
        List<Integer> range = new ArrayList<Integer>();
        for(int i = 0; i < height.length; i++){
            if(!range.contains(height[i])){
               range.add(height[i]);
            }
        }
        Collections.sort(range);
        for(int j = 0; j < range.size() - 1; j++){
            result = result + layer_trap(range.get(j), height);
 
        }
        return result;
    }
    private int layer_trap(int bottom, int[] height){
        int h_left = 0;
        int h_right = 0;
        int start = 0;
        int end = 0;
        int area = 0;
        for(int i = 0; i < height.length; i++){
            if(height[i] != bottom){
                continue;
            }else{
                if(i > 0 && height[i - 1] > bottom){
                    h_left = height[i - 1];
                    start = i;
                }
                if(i < height.length - 1 && height[i + 1] > bottom && h_left > bottom){
                    h_right = height[i + 1];
                    end = i;
                }
                if(h_left > bottom && h_right > bottom){
                    area = area + (Math.min(h_left, h_right) - bottom) * (end - start + 1);
                    for(int j = start; j <= end; j++){
                        height[j] = Math.min(h_left, h_right);
                    }
                    h_left = bottom;
                    h_right = bottom;
                }
            }
        }

        
        return area;
    }
}