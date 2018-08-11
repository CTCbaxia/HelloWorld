/*
MEDIUM
11. Container With Most Water
https://leetcode.com/problems/container-with-most-water/description/

TIME: 0524 - 2h
RESULT: 85%
*/
/*
Solution 1: 85%

思路：

总结：
看问题要抓住核心。这个题的 area 面积的最大值，一定是在一层层比较之后产出的。
所有最关心的就是前后循环的相对关系。
而前后循环里会影响 area 的就两个因素：1.底；2.高。
其中底肯定是每轮都会变，且变化单位为1；
关键是高。
这题里面只有小值的高起作用，所有问题转化为，如何求更大的小值高度。

而前后比较的思维得出，每次循环变一个值，只有让小值变化才有可能让 area 的值变大。（因为底的变化是固定的）
因为如果移动的是大值的高，那么 next area的面积只有比上一个可能更小，因为高只可能 <= 原有的小值的高；而底宽一定会减小；
所以面积是一定小于等于上一个面积的。
所有每次循环改变的值应该是小值的高。

至于如何想到从最外围开始往里缩进？
这种题肯定最好就循环一次，
两个指针最好能从极端位置开始。
*/
class Solution {
    public int maxArea(int[] height) {
        int area = 0;
        int tmp_area = 0;
        int l_h = 0;
        int r_h = height.length-1;
        for(int i = 0; i < height.length; i++){
            tmp_area = Math.min(height[l_h],height[r_h]) * (r_h - l_h);
            area = Math.max(area,tmp_area);
            if(height[l_h] <= height[r_h]){
                l_h ++;
            }else if (height[l_h] > height[r_h]){
                r_h --;
            }
            
        }
        return area;
    }
}


/*
Solution 0: 时间太长，fail

思路：
遍历所有的组合
*/


class Solution {
    public int maxArea(int[] height) {
        int area = 0;
        int tmp_area = 0;
        int l_h;
        int r_h;
        for(int i = 0; i < height.length; i++){
            for(int j = i+1; j < height.length; j++){
                l_h = height[i];
                r_h = height[j];
                tmp_area = Math.min(l_h, r_h) * (j - i);
                area = Math.max(area, tmp_area);
            }
        }
        return area;
    }
}


