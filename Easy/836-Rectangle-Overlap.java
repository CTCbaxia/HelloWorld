/*
EASY
836. Rectangle Overlap


TIME: 1011 - 15min
RESULT:100% - 1ms
NOTES: 

*/
//如果给恶心的数字会overflow
class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return isOverlap(rec1[0], rec1[2], rec2[0], rec2[2]) && isOverlap(rec1[1], rec1[3], rec2[1], rec2[3]);
    }
    private boolean isOverlap(int a1, int a2, int b1, int b2){
        int min = Math.min(a1, b1);
        int max = Math.max(a2, b2);
        if(max - min < (a2 - a1) + (b2 - b1)) return true;
        else return false;
    }
}

//想不可能的情况更简单
class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return (!(rec1[2] <= rec2[0] || rec1[3] <= rec2[1] || rec2[2] <= rec1[0] || rec2[3] <= rec1[1]));
    }

}
//直接找到如果相交的区域是否存在
class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int left = Math.max(rec1[0], rec2[0]);
        int right = Math.min(rec1[2], rec2[2]);
        int bottom = Math.max(rec1[1], rec2[1]);
        int up = Math.min(rec1[3], rec2[3]);
        return (right > left) && (up > bottom);
    }

}